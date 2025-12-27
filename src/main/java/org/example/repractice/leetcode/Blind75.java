package org.example.repractice.leetcode;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

class BitManipulations {

    public static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        int offset = 1;
        for (int i = 1; i < n + 1; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }

    public static int[] singleNumber(int[] nums) {
        int res = 0;
        for (Integer num: nums) {
            res = res ^ num;
        }
        int diff_bit = 1;
        while ((diff_bit & res) == 0) {
            diff_bit = diff_bit << 1;
        }

        int a = 0, b = 0;
        for (Integer num: nums) {
            if ((num & diff_bit) == 0) {
                a = a ^ num;
            } else {
                b = b ^ num;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(countBits(5)));
        System.out.println(Arrays.toString(singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }
}

class HashTables {

    private static String sortString(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return Arrays.toString(chars);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            String sorted = sortString(str);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }
        return map.values().stream().toList();
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int length;
        int maxLength = 0;
        for (Integer num: nums) {
            if (numSet.contains(num - 1)) {
                continue;
            }
            length = 1;
            while (numSet.contains(num + length)) {
                length += 1;
            }
            maxLength = max(maxLength, length);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        //System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}

class TwoPointers {

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;
        while (l < r) {
            maxArea = max(maxArea, (r - l) * (min(height[l], height[r])));
            if (height[r] > height[l]) {
                l += 1;
            } else {
                r -= 1;
            }
        }
        return maxArea;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int threeSum = nums[i] + nums[l] + nums[r];
                if (threeSum == 0) {
                    res.add(List.of(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                } else if (threeSum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    public static int trap(int[] height) {
        int maxLeft = Integer.MIN_VALUE, maxRight = Integer.MIN_VALUE;
        int l = 0, r = height.length - 1;
        int trap = 0;
        while (l < r) {
            if (maxLeft < maxRight) {
                l++;
                maxLeft = max(maxLeft, height[l]);
                trap += maxLeft - height[l];
            } else {
                r--;
                maxRight = max(maxRight, height[r]);
                trap += maxRight - height[r];
            }
        }
        return trap;
    }

    public static void main(String[] args) {
        //System.out.println(maxArea((new int[]{1,8,6,2,5,4,8,3,7})));
        //System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

class PrefixSum {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        int count = 0;
        int prefixSum = 0;
        for (Integer num: nums) {
            prefixSum += num;
            if (prefixCount.containsKey(prefixSum - k)) {
                count = 1 + prefixCount.get(prefixSum - num);
            }
            int finalPrefixSum = prefixSum;
            prefixCount.computeIfAbsent(
                    prefixSum, key -> 1 + prefixCount.getOrDefault(finalPrefixSum, 0));
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
    }
}

class FixedWindow {

    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            pCount.put(p.charAt(i), 1 + pCount.getOrDefault(p.charAt(i), 0));
            sCount.put(s.charAt(i), 1 + sCount.getOrDefault(s.charAt(i), 0));
        }
        if (pCount.equals(sCount)) {
            res.add(0);
        }
        System.out.println(sCount);
        int l = 0;
        for (int j = p.length(); j < s.length(); j++) {
            sCount.put(s.charAt(j), 1 + sCount.getOrDefault(s.charAt(j), 0));
            sCount.put(s.charAt(l), sCount.get(s.charAt(l)) - 1);
            if (sCount.get(s.charAt(l)) == 0) {
                sCount.remove(s.charAt(l));
            }
            l++;
            if (pCount.equals(sCount)) {
                res.add(l);
            }
        }
        return res;
    }

    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            pCount.put(s1.charAt(i), 1 + pCount.getOrDefault(s1.charAt(i), 0));
            sCount.put(s2.charAt(i), 1 + sCount.getOrDefault(s2.charAt(i), 0));
        }
        if (pCount.equals(sCount)) {
            return true;
        }
        System.out.println(sCount);
        int l = 0;
        for (int j = s1.length(); j < s2.length(); j++) {
            sCount.put(s2.charAt(j), 1 + sCount.getOrDefault(s2.charAt(j), 0));
            sCount.put(s2.charAt(l), sCount.get(s2.charAt(l)) - 1);
            if (sCount.get(s2.charAt(l)) == 0) {
                sCount.remove(s2.charAt(l));
            }
            l++;
            if (pCount.equals(sCount)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }
}

class DynamicSize {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = Integer.MIN_VALUE;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                map.remove(s.charAt(l));
                l++;
            }
            map.put(s.charAt(r), 1);
            maxLength = max(maxLength, r - l + 1);
        }
        return maxLength;
    }

    /*
    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     */
    public static String minWindow(String s, String t) {
        int l = 0;
        Map<Character, Long> tCount = t.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character, Long> sCount = new HashMap<>();
        int need = tCount.size();
        int have = 0;
        int maxLen = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            sCount.put(c, sCount.getOrDefault(c, 0L) + 1);
            if (tCount.containsKey(c) && Objects.equals(tCount.get(c), sCount.get(c))) {
                have += 1;
            }
            System.out.println(tCount);
            System.out.println(sCount);
            System.out.println(maxLen);
            while (have == need) {
                if (maxLen > r - l + 1) {
                    maxLen = r - l + 1;
                    res = new int[] {l, r};
                }
                Character lChar = s.charAt(l);
                sCount.put(lChar, sCount.get(lChar) - 1);
                if (tCount.containsKey(lChar) && tCount.get(lChar) > sCount.get(lChar)) {
                    have -= 1;
                }
                l++;
            }
        }
        l = res[0];
        int r = res[1];
        if (maxLen == Integer.MAX_VALUE) {
            return null;
        } else {
            return s.substring(l, r + 1);
        }
    }

    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}

class KadaneAlg {

    public static int maxSubArray(int[] nums) {
        int curSum = 0;
        int maxSum = 0;
        for (Integer num: nums) {
            curSum = max(curSum + num, num);
            maxSum = max(maxSum, curSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}

class MatrixProblems {

    public static int[][] rotate(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        while (left < right) {
            for (int i = 0; i < right - left; i++) {
                int top = left;
                int bottom = right;
                int temp = matrix[top][left + i];
                matrix[top][left + i] = matrix[bottom - i][left];
                matrix[bottom - i][left] = matrix[bottom][right - i];
                matrix[bottom][right - i] = matrix[top + i][right];
                matrix[top + i][right] = temp;
            }
            left++;
            right--;
        }
        return matrix;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0, top = 0;
        int bottom = matrix.length, right = matrix[0].length;

        while (left < right && top < bottom) {
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right - 1]);
            }
            right--;

            if (!(top < bottom) && (left < right)) {
                break;
            }

            for (int i = right - 1; i > left - 1; i--) {
                res.add(matrix[bottom - 1][i]);
            }
            bottom--;

            for (int i = bottom - 1; i > top - 1; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        //System.out.println(Arrays.deepToString(rotate(matrix)));
        System.out.println(spiralOrder(matrix));
    }
}

class LinkedListProblems {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i <= n; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        if (slow != null && slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

    public static void printLinkedList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.println("Current value: " + temp.val);
            temp = temp.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        while (cur != null && cur.next != null) {
            ListNode first = cur;
            ListNode second = cur.next;

            prev.next = second;
            first.next = second.next;
            second.next = first;

            prev = first;
            cur = first.next;
        }
        return dummy.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            int threeSum = val1 + val2 + carry;
            carry = threeSum / 10;
            int val = threeSum % 10;
            cur.next = new ListNode(val);

            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    private static ListNode getKthNode(ListNode node, int k) {
        while (k > 0 && node != null) {
            node = node.next;
            k -= 1;
        }
        return node;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode grpPrev = dummy;
        while (true) {
            ListNode kth = getKthNode(grpPrev, k);
            if (kth == null) {
                break;
            }
            ListNode grpNext = kth.next;
            ListNode prev = grpNext;
            ListNode cur = grpPrev.next;
            while (cur != grpNext) {
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            }

            ListNode temp = grpPrev.next;
            grpPrev.next = kth;
            grpPrev = temp;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode res = removeNthFromEnd(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
        ListNode res1 = swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
        ListNode res2 = addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))),
                new ListNode(5, new ListNode(6, new ListNode(4))));
        ListNode res3 = reverseKGroup(
                 new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
        printLinkedList(res3);
    }
}

public class Blind75 {
}
