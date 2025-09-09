package org.example.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Array {
    /*
    Input: nums = [2,2,1,1,1,2,2]
    Output: 2
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int majority = nums.length / 2;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > majority) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /*
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
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(nums));
        for (int i = nums.length - 2; i >= 0; i--) {
            prod *= nums[i + 1];
            res[i] *= prod;
        }
        return  res;
    }

    /*
    Input: nums = [1,2,0]
    Output: 3
    Explanation: The numbers in the range [1,2] are all in the array.
     */

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            if (val >= 1 && val <= n) {
                if (nums[val - 1] == 0) {
                    nums[val - 1] = -1 * (n + 1);
                } else if (nums[val - 1] > 0) {
                    nums[val - 1] *= -1;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (nums[i - 1] >= 0) {
                return i;
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {
        Array array = new Array();
        int[] nums = {2,2,1,1,1,2,2};
        //System.out.println(array.majorityElement(nums));
        //System.out.println(Arrays.toString(array.productExceptSelf(new int[]{1, 2, 3, 4})));
        //System.out.println(array.firstMissingPositive((new int[]{1, 3, 0})));
    }
}

class StringProblems {
    /*
    Input: s = "abc", t = "ahbgdc"
    Output: true
    */
    public boolean isSubsequence(String s, String t) {
        int s_length = s.length();
        int t_length = t.length();
        if (s_length > t_length) {
            return  false;
        }
        int length = 0;
        for (char c: t.toCharArray()) {
            if (s.charAt(length) == c) {
                length += 1;
            }
            if (length == s_length) {
                return true;
            }
        }
        return  false;
    }

    /*
    Input: s = "the sky is blue"
    Output: "blue is sky the"
     */
    public String reverseWords(String s) {
       String[] strings = s.trim().split("\\s+");
       List<String> res = new ArrayList<>();
       for (int i = strings.length - 1; i >= 0; i--) {
           res.add(strings[i]);
//           if (i > 0) {
//               res.add(" ");
//           }
       }
       StringBuilder sb = new StringBuilder();
       res.forEach(sb::append);
       String stream = Arrays.stream(strings).collect(Collectors.collectingAndThen(Collectors.toList(),
               list -> {
                Collections.reverse(list);
                return String.join(" ", list);
               }));
       System.out.println(stream);
       return String.join(" ", res);
    }


    public static void main(String[] args) {
        StringProblems string = new StringProblems();
        //System.out.println(string.isSubsequence("abc", "ahbgdc"));
        //System.out.println(string.reverseWords("the sky is blue"));
    }
}

class HashTables {

    /*
    Input: strs = ["eat","tea","tan","ate","nat","bat"]

    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Function<String, String> sort = string -> {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        };

        Map<String, List<String>> res = new HashMap<>();

        for (String str: strs) {
            String sortedString = sort.apply(str);
            res.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(str);
        }

        return res.values().stream().toList();
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();

        for (String str : strs) {
            int[] freq = new int[26]; // frequency count for a-z
            for (char c : str.toCharArray()) {
                freq[c - 'a']++;
            }

            // Convert frequency array to string key
            String key = Arrays.toString(freq);
            System.out.println(key);

            res.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(res.values()); // mutable list
    }

    /*
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     */

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int maxLength = 0;
        for (int num: nums) {
            if (!set.contains(num - 1)) {
                int length = 0;
                while (set.contains(num + length)) {
                    length += 1;
                }
                maxLength = max(maxLength, length);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        HashTables hashTables = new HashTables();
        //System.out.println(hashTables.groupAnagrams1(new String[] {"eat","tea","tan","ate","nat","bat"}));
        //System.out.println(hashTables.longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}

class TwoPointers {

    /*
    Input: height = [1,8,6,2,5,4,8,3,7]
    Output: 49
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            maxArea = max(maxArea, min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    /*
    Input: nums = [-1,0,1,2,-1,-4]
    Output: [[-1,-1,2],[-1,0,1]]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = n - 1;
            int totalSum = 0;
            while (left < right) {
                totalSum = nums[i] + nums[left] + nums[right];
                if (totalSum == 0) {
                    res.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (totalSum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return  res;
    }

    /*
    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
    In this case, 6 units of rain water (blue section) are being trapped.
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int res = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = max(leftMax, height[left]);
                res += leftMax - height[left];
            } else {
                right--;
                rightMax = max(rightMax, height[right]);
                res += rightMax - height[right];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TwoPointers twoPointers = new TwoPointers();
        //System.out.println(twoPointers.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        //System.out.println(twoPointers.threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(twoPointers.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

class PrefixSumProblem {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        int prefixSum = 0;
        for (Integer num: nums) {
            prefixSum += num;
            if (map.containsKey(k - prefixSum)) {
                res += map.get(k - prefixSum);
            }
            int finalPrefixSum = prefixSum;
            map.computeIfAbsent(prefixSum, key -> 1 + map.getOrDefault(finalPrefixSum, 0));
        }
        return res;
    }

    public static void main(String[] args) {
        PrefixSumProblem prefixSum = new PrefixSumProblem();
        System.out.println(prefixSum.subarraySum(new int[]{1,1,1}, 3));
    }
}

class FixedSizeSlidingWindow {
    /*
    Input: s = "cbaebabacd", p = "abc"
    Output: [0,6]
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
     */
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < p.length(); i++) {
            char c1 = p.charAt(i);
            char c2 = s.charAt(i);
            pMap.put(c1, pMap.getOrDefault(c1, 0) + 1);
            sMap.put(c2, sMap.getOrDefault(c2, 0) + 1);
        }
        if (pMap.equals(sMap)) {
            res.add(0);
        }

        int left = 0;
        for (int i = p.length(); i < s.length(); i++) {
            char c = s.charAt(left);
            sMap.put(c, sMap.get(c) - 1);
            if (sMap.get(c) == 0) {
                sMap.remove(c);
            }
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            left++;
            if (pMap.equals(sMap)) {
                res.add(left);
            }
        }
        return res;
    }

    /*
     Input: s1 = "ab", s2 = "eidbaooo"
     Output: true
     Explanation: s2 contains one permutation of s1 ("ba").
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            pMap.put(c1, pMap.getOrDefault(c1, 0) + 1);
            sMap.put(c2, sMap.getOrDefault(c2, 0) + 1);
        }
        if (pMap.equals(sMap)) {
            return true;
        }

        int left = 0;
        for (int i = s1.length(); i < s2.length(); i++) {
            char c = s2.charAt(left);
            sMap.put(c, sMap.get(c) - 1);
            if (sMap.get(c) == 0) {
                sMap.remove(c);
            }
            sMap.put(s2.charAt(i), sMap.getOrDefault(s2.charAt(i), 0) + 1);
            left++;
            if (pMap.equals(sMap)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FixedSizeSlidingWindow fixedSizeSlidingWindow = new FixedSizeSlidingWindow();
        //System.out.println(fixedSizeSlidingWindow.findAnagrams("cbaebabacd", "abc"));
        System.out.println(fixedSizeSlidingWindow.checkInclusion("ab", "eidbaooo"));
    }
}


public class Blind75 {
}
