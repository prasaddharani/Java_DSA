package main.leetcode;

import java.util.*;
import java.util.HashMap;

public class ArrayProblems {
    public static void main(String[] args) {
        //System.out.println(Arrays.toString(moveZeroes(new int[]{0, 1, 0, 3, 12})));
        //System.out.println(maxProfit_with1dayhold(new int[]{7,1,5,3,6,4}));
        //System.out.println(isSubsequence("axc", "ahbgdc"));
        //System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    /*
    283. Move Zeros
    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]
    */
    public static int[] moveZeroes(int[] nums) {
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] == 0 && nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            if (nums[left] != 0) {
                left += 1;
            }
        }
        return nums;
    }

    /*
    169. Majority Element
    Input: nums = [3,2,3]
    Output: 3
    The majority element is the element that appears more than ⌊n / 2⌋ times.
     You may assume that the majority element always exists in the array.
     */

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> majorityMap = new java.util.HashMap<>();
        for (Integer num: nums) {
            majorityMap.put(num, 1 + majorityMap.getOrDefault(num, 0));
        }
        int m = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry: majorityMap.entrySet()) {
            if (entry.getValue() > m) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /*
    26. Remove Duplicates from Sorted Array
    Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     */
    public int removeDuplicates(int[] nums) {
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                int temp = nums[left + 1];
                nums[left + 1] = nums[right];
                nums[right] = temp;
                left += 1;
            }
        }
        return left + 1;
    }

    /*
    121. Best Time to Buy and Sell Stock
    Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     */

    public int maxProfit(int[] prices) {
        int left = 0;
        int maxProfit = 0;
        for (int right = 1; right < prices.length; right++) {
            if (prices[right] > prices[left]) {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
            } else {
                left = right;
            }
        }
        return maxProfit;
    }

    /*
    122. Best Time to Buy and Sell Stock II
    Input: prices = [7,1,5,3,6,4]
    Output: 7
    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
    Total profit is 4 + 3 = 7.
     */
    public static int maxProfit_with1dayhold(int[] prices) {
        int totalProfit = 0;
        int left = 0;
        for (int right = 1; right < prices.length; right++) {
            if (prices[right] > prices[left]) {
                totalProfit += prices[right] - prices[left];
            }
            left += 1;
        }
        return totalProfit;
    }

    /*
    238. Product of Array Except Self
    Input: nums = [1,2,3,4]
    Output: [24,12,8,6]
     */

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int prod = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= prod;
            prod *= nums[i];
        }
        return res;
    }

    /*
    2348. Number of Zero-Filled Subarrays
    Input: nums = [1,3,0,0,2,0,0,4]
    Output: 6
    Explanation:
    There are 4 occurrences of [0] as a subarray.
    There are 2 occurrences of [0,0] as a subarray.
    There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
     */

    public long zeroFilledSubarray(int[] nums) {
        long res = 0;
        long count = 0;
        for (Integer num: nums) {
            if (num == 0) {
                count += 1;
            } else {
                count = 0;
            }
            res += count;
        }
        return res;
    }

    /*
    334. Increasing Triplet Subsequence
    Input: nums = [1,2,3,4,5]
    Output: true
    Explanation: Any triplet where i < j < k is valid.
     */

    public boolean increasingTriplet(int[] nums) {
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;
        for (Integer num: nums) {
            if (num <= i) {
                i = num;
            } else if (num <= j) {
                j = num;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
    41. First Missing Positive
    Input: nums = [1,2,0]
    Output: 3
    Explanation: The numbers in the range [1,2] are all in the array.
     */

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (nums.length == 0) return 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            if (1 <= val && val <= n) {
                if (nums[val - 1] > 0) {
                    nums[val - 1] *= -1;
                } else if (nums[val - 1] == 0) {
                    nums[val - 1] = -1 * (n + 1);
                }
            }
        }

        for (int i = 1; i <=n; i++) {
            if (nums[i - 1] >= 0) {
                return i;
            }
        }
        return n + 1;
    }

    /*
    189. Rotate Array
    Input: nums = [1,2,3,4,5,6,7], k = 3
    Output: [5,6,7,1,2,3,4]
     */

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) {
            return;
        }
        int[] temp = new int[k];
        System.arraycopy(nums, n - k, temp, 0, k);
        System.arraycopy(nums, 0, nums, k, n - k);
        System.arraycopy(temp, 0, nums, 0, k);
    }

    /*
    392. Is Subsequence
    Input: s = "abc", t = "ahbgdc"
    Output: true
     */

    public static boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }

        if (s.length() > t.length()) {
            return false;
        }

        int left = 0;
        for (int right = 0; right < t.length(); right++) {
            if (s.charAt(left) == t.charAt(right)) {
                left++;
            }

            if (left == s.length()) {
                return true;
            }
        }
        return false;
    }

    /*
    125. Valid Palindrome
    Input: s = "A man, a plan, a canal: Panama"
    Output: true
    Explanation: "amanaplanacanalpanama" is a palindrome.
     */

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !(Character.isAlphabetic(s.charAt(left)) || Character.isDigit(s.charAt(left)))) {
                left++;
            }
//            while (left < right && !isalphaNumeric(s.charAt(left))) {
//                left++;
//            }

            while (left < right && !isalphaNumeric(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isalphaNumeric(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57);
    }

    /*
    14. Longest Common Prefix
    Input: strs = ["flower","flow","flight"]
    Output: "fl"
     */

    public String longestCommonPrefix(String[] strs) {

        int length = strs.length - 1;
        Arrays.sort(strs);
        int minLength = Math.min(strs[0].length(), strs[length].length());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            if (strs[0].charAt(i) == strs[length].charAt(i)) {
                stringBuilder.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return String.valueOf(stringBuilder);
    }

    /*
    151. Reverse Words in a String
    Input: s = "the sky is blue"
    Output: "blue is sky the"
     */

    public String reverseWords(String s) {
        String[] strings = s.trim().split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            stringBuilder.append(strings[i]);
            if (i > 0) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    /*
    6. Zigzag Conversion

    Input: s = "PAYPALISHIRING", numRows = 3
    Output: "PAHNAPLSIIGYIR"
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        // Create an array of StringBuilder for each row.
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int row = 0;
        int step = 0;
        // Traverse the string character by character.
        for (char c : s.toCharArray()) {
            rows[row].append(c);
            if (row == 0) {
                step = 1;
            } else if (row == numRows - 1) {
                step = -1;
            }
            row += step;
        }

        // Build the final result by concatenating all rows.
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rows) {
            result.append(sb);
        }
        return result.toString();
    }

}
