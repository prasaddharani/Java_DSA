package org.example.leetcode;

import java.util.*;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Math.*;

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
            int val = abs(nums[i]);
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

class DynamicSizeSlidingWindow {
    /*
    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            Character c = s.charAt(right);
            while (map.containsKey(c)) {
                map.remove(c);
                left++;
            }
            map.put(c, 1);
            maxLength = max(maxLength, right - left + 1);
        }
        return maxLength;
    }


    /*
    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     */
    public String minWindow(String s, String t) {

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> have = new HashMap<>();
        for (Character c: t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int haveCount = 0;
        int needCount = need.size();
        int left = 0;
        int right;
        int resLen = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (right = 0; right < s.length(); right++) {
            Character c = s.charAt(right);
            have.put(c, have.getOrDefault(c, 0) + 1);
            if (need.containsKey(c) && Objects.equals(need.get(c), have.get(c))) {
              haveCount++;
            }

            while (haveCount == needCount) {
                int length = right - left + 1;
                if (resLen > length) {
                    res[0] = left;
                    res[1] = right;
                    resLen = length;
                }
                Character c1 = s.charAt(left);
                have.put(c1, have.get(c1) - 1);
                if (need.containsKey(c1) && need.get(c1) > have.get(c1)) {
                    haveCount -= 1;
                }
                left++;
            }
        }
        left = res[0];
        right = res[1];
        return resLen == Integer.MAX_VALUE? "": s.substring(left, right + 1);
    }

    public static void main(String[] args) {
        DynamicSizeSlidingWindow dynamicSizeSlidingWindow = new DynamicSizeSlidingWindow();
        //System.out.println(dynamicSizeSlidingWindow.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(dynamicSizeSlidingWindow.minWindow("ADOBECODEBANC", "ABC"));
    }
}

class KadanesAlgorithm {
    /*
    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
     */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (Integer num: nums) {
            currSum = max(currSum + num, num);
            maxSum = max(maxSum, currSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        KadanesAlgorithm kadanesAlgorithm = new KadanesAlgorithm();
        System.out.println(kadanesAlgorithm.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}

class MatrixProblem {

    /*
    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [1,2,3,6,9,8,7,4,5]
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0, left = 0;
        int right = matrix[0].length, bottom = matrix.length;
        List<Integer> res = new ArrayList<>();

        while (left < right && top < bottom) {
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right - 1]);
            }
            right--;

            if (!(left < right && top < bottom)) {
                break;
            }

            for (int i = right - 1; i > left - 1; i--) {
                res.add(matrix[bottom - 1][i]);
            }
            bottom--;

            for(int i = bottom - 1; i > top - 1; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return  res;
    }

    /*
    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [[7,4,1],[8,5,2],[9,6,3]]
     */
    public int[][] rotate(int[][] matrix) {
        int left = 0, right = matrix[0].length - 1;
        while (left < right) {
            for (int i = 0; i < right - left; i++) {
                int top = left, bottom = right;
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

    public static void main(String[] args) {
        MatrixProblem matrixProblem = new MatrixProblem();
        //System.out.println(matrixProblem.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Arrays.deepToString(matrixProblem.rotate(matrix)));
    }
}

class BitManipulation {

    /*
    Input: n = 5
    Output: [0,1,1,2,1,2]
    Explanation:
    0 --> 0
    1 --> 1
    2 --> 10
    3 --> 11
    4 --> 100
    5 --> 101
     */
    public int[] countBits(int n) {
        int offset = 1;
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }

    /*
    Input: nums = [1,2,1,3,2,5]
    Output: [3,5]
    Explanation:  [5, 3] is also a valid answer.
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num; // XOR of all numbers
        }

        // Find rightmost set bit
        int diffBit = 1;
        while ((diffBit & xor) == 0) {
            diffBit <<= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & diffBit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        BitManipulation bitManipulation = new BitManipulation();
        //System.out.println(Arrays.toString(bitManipulation.countBits(5)));
        System.out.println(Arrays.toString(bitManipulation.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }
}

class LinkedListProblem {

    /*
    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]
     */

    static class ListNode {
        int value;
        ListNode next;

        ListNode() {}
        ListNode(int value) {
            this.value = value;
        }

        ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow != null && slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

    /*
    Input: head = [1,2,3,4]

    Output: [2,1,4,3]
     */
    public ListNode swapPairs(ListNode head) {
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

    public void printLinkedList(ListNode node) {
        ListNode temp = node;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    /*
    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int val1 = l1 != null? l1.value: 0;
            int val2 = l2 != null? l2.value: 0;

            int totalSum = val1 + val2 + carry;
            carry = totalSum / 10;
            int val = totalSum % 10;
            cur.next = new ListNode(val);

            cur = cur.next;
            l1 = l1 != null? l1.next: null;
            l2 = l2 != null? l2.next: null;
        }
        return dummy.next;
    }


    /*
    Input: head = [1,2,3,4,5], k = 2
    Output: [2,1,4,3,5]
     */

    public ListNode getKthNode(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k -= 1;
        }
        return node;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode grpPrev = dummy;
        while (true) {
            ListNode kthNode = getKthNode(grpPrev, k);
            if (kthNode == null) {
                break;
            }

            ListNode grpNext = kthNode.next;

            ListNode cur = grpPrev.next;
            ListNode prev = grpNext;

            while (cur != grpNext) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            ListNode temp = grpPrev.next;
            grpPrev.next = kthNode;
            grpPrev = temp;
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
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /*
    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    merging them into one sorted linked list:
    1->1->2->3->4->4->5->6
     */

    public ListNode mergeSort(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeSort(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    public static void main(String[] args) {
        LinkedListProblem linkedListProblem = new LinkedListProblem();
//        ListNode res = linkedListProblem.removeNthFromEnd(
//                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
//        res = linkedListProblem.swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
//        res = linkedListProblem.addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))),
//                new ListNode(5, new ListNode(6, new ListNode(4))));
//        ListNode res = linkedListProblem.reverseKGroup(
//                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
        // Build the list: 3 -> 2 -> 0 -> -4
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);

        // Create cycle: -4 -> 2
        head.next.next.next.next = head.next;
        //ListNode res = linkedListProblem.detectCycle(head);
        ListNode res = linkedListProblem.mergeKLists(new ListNode[]{
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(2, new ListNode(6))
        });
        linkedListProblem.printLinkedList(res);
    }
}


class StackProblem {

    /*
    Input: s = "()[]{}"
    Output: true
     */

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (Character c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() &&
                        ((stack.peek() == '(' && c == ')') ||
                                (c == ']' && stack.peek() == '[')) ||
                        (c == '}' && stack.peek() == '{')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /*
    ["MinStack","push","push","push","getMin","pop","top","getMin"]
    [[],[-2],[0],[-3],[],[],[],[]]
     */
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || (minStack.peek() >= val)) {
                minStack.push(val);
            }
        }

        public void pop() {
            int val = stack.pop();
            if (minStack.peek() == val) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    static class Histogram {

        private int index;
        private int height;

        Histogram (int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    /*
    heights = [2,1,5,6,2,3]
    output: 10
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Histogram> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int index = i;
            int height = heights[i];
            while (!stack.isEmpty() && stack.peek().height > height) {
                Histogram histogram = stack.pop();
                index = histogram.index;
                int h = histogram.height;
                maxArea = max(maxArea, h * (i - index));
            }
            stack.push(new Histogram(index, height));
        }

        for (Histogram histogram: stack) {
            int index = histogram.index;
            int height = histogram.height;
            maxArea = max(maxArea, height * (heights.length - index));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        StackProblem stackProblem = new StackProblem();
        //System.out.println(stackProblem.isValid("()[]{}"));
//        MinStack minStack = new StackProblem().new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin()); // return -3
//        minStack.pop();
//        System.out.println(minStack.top());    // return 0
//        System.out.println(minStack.getMin()); // return -2
        System.out.println(stackProblem.largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
}

class QueueProblem {

    /*
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [3,3,5,5,6,7]
    Explanation:
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
     */

    static class MaxWindow {
        int index;
        int value;

        MaxWindow (int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Queue<MaxWindow> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {

            while (!queue.isEmpty() && queue.peek().index - i > k + 1) {
                queue.poll();
            }

            while (!queue.isEmpty() && queue.peek().value < nums[i]) {
                queue.poll();
            }

            queue.add(new MaxWindow(i, nums[i]));

            if (i >= k - 1) {
                res.add(queue.peek().value);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        QueueProblem queueProblem = new QueueProblem();
        System.out.println(Arrays.toString(queueProblem.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}

class Sorting {

    /*
    Input: nums = [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]
     */
    public int[] sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[mid] ;
                nums[mid] = nums[low];
                nums[low] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 2) {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            } else {
                mid++;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        System.out.println(Arrays.toString(sorting.sortColors(new int[]{2, 0, 2, 1, 1, 0})));
    }
}


class BinarySearchProblem {

    /*
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]
     */
    private int binarySearch (int[] nums, int target, boolean leftBias) {
        int left = 0, right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                res = mid;
                if (leftBias) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);
        return new int[]{left, right};
    }

    /*
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4
     */

    public int binarySearchHelper(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target < nums[mid]) {
                return binarySearchHelper(nums, target, left, mid - 1);
            } else {
                return binarySearchHelper(nums, target, mid + 1, right);
            }
        } else {
            if (target > nums[mid] && target <= nums[right]) {
                return binarySearchHelper(nums, target, mid + 1, right);
            } else {
                return binarySearchHelper(nums, target, left, mid - 1);
            }
        }
    }

    public int search(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }

    /*
    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = (nums1.length + nums2.length) / 2;
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length;
        int y = nums2.length;
        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE: nums1[partitionX - 1];
            int minRightX = partitionX == x? Integer.MAX_VALUE: nums1[partitionX];

            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE: nums2[partitionY - 1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE: nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if (total % 2 == 0) {
                    return (double) (max(minRightX, minRightY) + min(maxLeftX, maxLeftY)) / 2;
                } else {
                    return max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY){
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearchProblem binarySearchProblem = new BinarySearchProblem();
        //System.out.println(Arrays.toString(binarySearchProblem.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        //System.out.println(binarySearchProblem.search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(binarySearchProblem.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}

class Backtrack {

    /*
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */

    public void backtrack(List<List<Integer>> res, Stack<Integer> path, boolean[] used, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtrack(res, path, used, nums);
            used[i] = false;
            path.pop();
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        backtrack(res, new Stack<>(), new boolean[nums.length], nums);
        return res;
    }

    /*
    Input: nums = [1,2,3]
    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    public void backtrack(List<List<Integer>> res, int start, Stack<Integer> path, int[] nums) {
        res.add(new ArrayList<>(path));

        for (int end = start; end < nums.length; end++) {
            path.add(nums[end]);
            backtrack(res, end + 1, path, nums);
            path.pop();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, 0, new Stack<>(), nums);
        return res;
    }

    public static void main(String[] args) {
        Backtrack backtrack = new Backtrack();
        //System.out.println(backtrack.permute(new int[]{1, 2, 3}));
        System.out.println(backtrack.subsets(new int[]{1, 2, 3}));
    }
}

class TreeProblems {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int value) {
            this.value = value;
        }

        TreeNode(int value, TreeNode left, TreeNode right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }
    }

    /*
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                level.add(node.value);
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    /*
        Input: root = [1,2,3,null,5,null,4]
        Output: [1,3,4]
     */

    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                level.add(node.value);
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level.get(levelSize - 1));
        }
        return res;
    }

    /*
    Input: root = [1,null,2,3]
    Output: [1,2,3]
     */

    public void dfs(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.value);

        if (node.left != null) {
            dfs(node.left, res);
        }

        if (node.right != null) {
            dfs(node.right, res);
        }
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }


    public int dfs(TreeNode node, Map<Integer, Integer> countMap, int targetSum, int curr_sum) {
        if (node == null) {
            return 0;
        }
        curr_sum += node.value;
        int count = countMap.getOrDefault(curr_sum - targetSum, 0);
        countMap.put(curr_sum, countMap.getOrDefault(curr_sum, 0) + 1);

        count += dfs(node.left, countMap, targetSum, curr_sum);
        count += dfs(node.right, countMap, targetSum, curr_sum);
        countMap.put(curr_sum, countMap.get(curr_sum) - 1);
        return count;
    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        int curr_sum = 0;
        return dfs(root, countMap, targetSum, curr_sum);
    }

    public class Codec {
        public void dfsSerialize(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("N,");
                return;
            }

            sb.append(node.value).append(",");
            dfsSerialize(node.left, sb);
            dfsSerialize(node.right, sb);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfsSerialize(root, sb);
            return sb.toString();
        }

        public TreeNode dfsDeserialize(Queue<String> queue) {
            String data = queue.poll();
            if (Objects.equals(data, "N")) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(data));
            root.left = dfsDeserialize(queue);
            root.right = dfsDeserialize(queue);
            return root;
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] values = data.split(",");
            Queue<String> queue = new LinkedList<>(Arrays.asList(values));
            return dfsDeserialize(queue);
        }
    }

    public void inOrder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inOrder(node.left, res);
        res.add(node.value);
        inOrder(node.right, res);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        int prevVal = res.get(0);
        for (int i = 1; i < res.size(); i++) {
            if (prevVal > res.get(i)) {
                return false;
            }
            prevVal = res.get(i);
        }
        return true;
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res.get(k - 1);
    }

    public void postOrder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }

        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.value);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null? left: right;
    }

    /*
    124. Binary Tree Maximum Path Sum
    Input: root = [1,2,3]
    Output: 6
    Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
     */

    private int maxSum;

    public int dfsMaxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMax = max(dfsMaxPathSum(node.left), 0);
        int rightMax = max(dfsMaxPathSum(node.right), 0);
        int currSum = node.value + leftMax + rightMax;
        maxSum = max(this.maxSum, currSum);
        return node.value + max(leftMax, rightMax);
    }

    public int maxPathSum(TreeNode root) {
        dfsMaxPathSum(root);
        return maxSum;
    }

    public static void main(String[] args) {
        TreeProblems treeProblems = new TreeProblems();
//        System.out.println(treeProblems.levelOrder(new TreeNode(3, new TreeNode(9),
//                new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
//        System.out.println(treeProblems.rightSideView(new TreeNode(1,
//                new TreeNode(2, null, new TreeNode(5)),
//                new TreeNode(3, null, new TreeNode(4)))));
//        System.out.println(treeProblems.preorderTraversal(new TreeNode(1, null,
//                new TreeNode(2, new TreeNode(3), null))));
//        System.out.println(
//                treeProblems.pathSum(
//                        new TreeNode(10,
//                                new TreeNode(5,
//                                        new TreeNode(3,
//                                                new TreeNode(3),
//                                                new TreeNode(-2)
//                                        ),
//                                        new TreeNode(2,
//                                                null,
//                                                new TreeNode(1)
//                                        )
//                                ),
//                                new TreeNode(-3,
//                                        null,
//                                        new TreeNode(11)
//                                )
//                        ),
//                        8
//                )
//        );
//        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
//        Codec codec = treeProblems.new Codec();
//        String ser = codec.serialize(root);
//        System.out.println("Serialized: " + ser);
//        TreeNode des = codec.deserialize(ser);
//        System.out.println("Deserialized Root: " + des.value);  // should print 1

        //System.out.println(treeProblems.inorderTraversal(new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null))));
        //System.out.println(treeProblems.isValidBST(new TreeNode(2, new TreeNode(1), new TreeNode(3))));
//        System.out.println(treeProblems.kthSmallest(
//                new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4)), 1));
//        System.out.println(treeProblems.postorderTraversal(new TreeNode(1,
//                null,
//                new TreeNode(2,
//                        new TreeNode(3),
//                        null))));
//        TreeNode root = new TreeNode(3,
//                new TreeNode(5,
//                        new TreeNode(6),
//                        new TreeNode(2,
//                                new TreeNode(7),
//                                new TreeNode(4)
//                        )
//                ),
//                new TreeNode(1,
//                        new TreeNode(0),
//                        new TreeNode(8)
//                )
//        );
//
//        System.out.println(treeProblems.lowestCommonAncestor(root, root.left, root.right).value);
        TreeNode root = new TreeNode(10,
                new TreeNode(2,
                        new TreeNode(20),
                        new TreeNode(1)
                ),
                new TreeNode(10,
                        null,
                        new TreeNode(-25,
                                new TreeNode(3),
                                new TreeNode(4)
                        )
                )
        );

        System.out.println("Max Path Sum = " + treeProblems.maxPathSum(root));
    }

    /*
    Input
    ["MyCalendar", "book", "book", "book"]
    [[], [10, 20], [15, 25], [20, 30]]
    Output
    [null, true, false, true]
     */

    static class CalendarTreeNode {
        int start;
        int end;
        CalendarTreeNode left;
        CalendarTreeNode right;

        CalendarTreeNode() {}

        CalendarTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }

        CalendarTreeNode(int start, int end, CalendarTreeNode left, CalendarTreeNode right) {
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
        }
    }
    static class MyCalendar {
        CalendarTreeNode root;
        public MyCalendar() {
            root = null;
        }

        public boolean book(int startTime, int endTime) {
            if (root == null) {
                root = new CalendarTreeNode(startTime, endTime);
                return true;
            }
            CalendarTreeNode cur = root;
            while (cur != null) {
                if (cur.start >= endTime) {
                    if (cur.left == null) {
                        cur.left = new CalendarTreeNode(startTime, endTime);
                        return true;
                    }
                    cur = cur.left;
                } else if (cur.end <= startTime) {
                    if (cur.right == null) {
                        cur.right = new CalendarTreeNode(startTime, endTime);
                        return true;
                    }
                    cur = cur.right;
                } else {
                    return false;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            MyCalendar myCalendar = new MyCalendar();
            System.out.println(myCalendar.book(10, 20)); // return True
            System.out.println(myCalendar.book(15, 25)); // return False, It can not be booked because time 15 is already booked by another event.
            System.out.println(myCalendar.book(20, 30)); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
        }
    }


    /*
    Input
    ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
    [[], [1], [2], [], [3], []]
    Output
    [null, null, null, 1.5, null, 2.0]
     */
    static class MedianFinder {
        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            maxHeap.add(num);
            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                minHeap.add(maxHeap.poll());
            }

            if (minHeap.size() > 1 + maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            if (maxHeap.size() > 1 + minHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
        }

        public double findMedian() {
            if (minHeap.size() > maxHeap.size()) {
                return minHeap.peek();
            }

            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }

            return (double) (minHeap.peek() + maxHeap.peek()) /2;
        }

        public static void main(String[] args) {
            MedianFinder medianFinder = new MedianFinder();
            medianFinder.addNum(1);    // arr = [1]
            medianFinder.addNum(2);    // arr = [1, 2]
            System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
            medianFinder.addNum(3);    // arr[1, 2, 3]
            System.out.println(medianFinder.findMedian()); // return 2.0
        }
    }

    static class HeapProblems {
        static class Pair {
            Integer key;
            Integer value;

            Pair(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
        }
        public int[] topKFrequent(int[] nums, int k) {
            Queue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer num: nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                queue.add(new Pair(entry.getKey(), entry.getValue()));
            }
            int[] res = new int[k];
            int i = 0;
            while (k > 0) {
                res[i] = queue.poll().value;
                k -= 1;
                i += 1;
            }
            return res;
        }

        public static void main(String[] args) {
            HeapProblems heapProblems = new HeapProblems();
            System.out.println(Arrays.toString(heapProblems.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        }
    }

    /*
    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
     */
    static class IntervalProblems {

        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            List<int[]> res = new ArrayList<>();
            int[] current = intervals[0];
            res.add(current);

            for (int[] interval: intervals) {
                if (interval[0] <= current[1]) {
                    current[1] = max(interval[1], current[1]);
                } else {
                    current = interval;
                    res.add(interval);
                }
            }
            return res.toArray(new int[res.size()][]);
        }


        /*
        Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
        Output: 1
        Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            int count = 0;
            int[] current = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < current[1]) {
                    current[1] = min(intervals[i][1], current[1]);
                    count++;
                }
            }
            return count;
        }

        public static void main(String[] args) {
            IntervalProblems intervalProblems = new IntervalProblems();
            //System.out.println(Arrays.deepToString(intervalProblems.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
            System.out.println(intervalProblems.eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
        }
    }

    static class SystemDesign {
        static class LinkedList {
            int key;
            int value;
            LinkedList prev;
            LinkedList next;

            LinkedList() {}
            LinkedList(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        static class LRUCache {
            Map<Integer, LinkedList> cache;
            int capacity;
            LinkedList head, tail;

            public LRUCache(int capacity) {
                this.cache = new HashMap<>();
                this.capacity = capacity;
                head = new LinkedList();
                tail = new LinkedList();
                head.next = tail;
                tail.prev = head;
            }

            public void remove(LinkedList node) {
                LinkedList prev = node.prev, next = node.next;
                prev.next = next;
                next.prev = prev;
            }

            public void add(LinkedList node) {
                node.prev = head;
                node.next = head.next;
                head.next.prev = node;
                head.next = node;
            }

            public int get(int key) {
                if (cache.containsKey(key)) {
                    LinkedList node = cache.get(key);
                    remove(node);
                    add(node);
                    return node.value;
                }
                return -1;
            }

            public void put(int key, int value) {
                LinkedList node;
                if (cache.containsKey(key)) {
                    node = cache.get(key);
                    node.value = value;
                    cache.put(key, node);
                    remove(node);
                    add(node);
                } else {
                    node = new LinkedList(key, value);
                    cache.put(key, node);
                    add(node);
                    if (cache.size() > capacity) {
                        LinkedList lruNode = tail.prev;
                        remove(lruNode);
                    }
                }
            }
        }

        public static void main(String[] args) {
            LRUCache obj = new LRUCache(2);
            System.out.println(obj.get(2));  // -1
            obj.put(1, 5);
            obj.put(2, 10);
            System.out.println(obj.get(1));  // 5
            obj.put(3, 15);  // evicts key 2
            System.out.println(obj.get(2));  // -1
            System.out.println(obj.get(3));  // 15
        }
    }

    static class Greedy {
        /*
        Input: nums = [2,3,1,1,4]
        Output: true
        Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
        */
        public boolean jump(int[] nums) {

            int maxJump = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > maxJump) {
                    return false;
                }
                maxJump = max(maxJump, i + nums[i]);
                if (maxJump > nums.length) {
                    return true;
                }
            }
            return false;
        }

        public int jumpGame(int[] nums) {
            int curJump = 0;
            int maxJump = 0;
            int res = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                maxJump = max(maxJump, i + nums[i]);
                if (curJump == i) {
                    curJump = maxJump;
                    res++;
                }
                if (curJump > nums.length - 1) {
                    return res;
                }
            }
            return res;
        }

        public static void main(String[] args) {
            Greedy greedy = new Greedy();
            System.out.println(greedy.jump(new int[]{2,3,1,1,4}));
            System.out.println(greedy.jumpGame(new int[]{2,3,1,1,4}));
        }
    }

    static class DynamicProgramming {

        /*
        Input: nums = [2,3,2]
        Output: 3
        Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
         */
        public int robHelper(int[] nums) {
            if (nums.length < 2) {
                return nums[0];
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = max(dp[0], dp[1]);

            for (int i = 2; i < nums.length; i++) {
                dp[i] = max(nums[i] + dp[i - 2], dp[i - 1]);
            }
            return dp[nums.length - 1];
        }

        public int rob(int[] nums) {
            int excludeFist = robHelper(Arrays.copyOfRange(nums, 0, nums.length - 2));
            int excludeLast = robHelper(Arrays.copyOfRange(nums, 1, nums.length - 1));
            return max(excludeFist, excludeLast);
        }

        /*
        Input: nums = [1,5,11,5]
        Output: true
        Explanation: The array can be partitioned as [1, 5, 5] and [11].
         */
        public boolean canPartition(int[] nums) {

            int total = Arrays.stream(nums).sum();
            if (total % 2 != 0) {
                return false;
            }
            int target = total / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            for (int num : nums) {
                for (int i = target; i >= num; i--) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
            return dp[target];
        }

        /*
        Input: coins = [1,2,5], amount = 11
        Output: 3
        Explanation: 11 = 5 + 5 + 1
         */
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                        dp[i] = min(dp[i], 1 + dp[i - coin]);
                    }
                }
            }
            return dp[amount];
        }

        /*
        Input: nums = [10,9,2,5,3,7,101,18]
        Output: 4
        Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
         */
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = max(dp[i], dp[j] + 1);
                    }
                }
            }
            return dp[nums.length - 1];
        }

        public int lengthOfLIS1(int[] nums) {
            List<Integer> sub = new ArrayList<>();
            for (int num : nums) {
                int idx = Collections.binarySearch(sub, num);
                if (idx < 0) {
                    idx = -(idx + 1);
                }
                if (idx == sub.size()) {
                    sub.add(num);
                } else {
                    sub.set(idx, num);
                }
            }
            return sub.size();
        }

        /*
        Input: text1 = "abcde", text2 = "ace"
        Output: 3
        Explanation: The longest common subsequence is "ace" and its length is 3.
         */
        public int longestCommonSubsequence(String text1, String text2) {
            int rows = text1.length();
            int cols = text2.length();
            int[][] dp = new int[rows + 1][cols + 1];
            System.out.println(Arrays.deepToString(dp));
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[rows][cols];
        }

        /*
        Input: s = "applepenapple", wordDict = ["apple","pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        Note that you are allowed to reuse a dictionary word.
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            int max_len = wordDict.stream().mapToInt(String::length).max().orElse(0);

            for (int i = 1; i <= s.length(); i++) {
                for (int j = i - 1; j >= max(0, i - max_len); j--) {
                    if (dp[j] && wordDict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;  // no need to check further
                    }
                }
            }
            return dp[s.length()];
        }

        /*
        Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
        Output: 7
        Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
         */
        public int minPathSum(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            int[][] dp = new int[rows + 1][cols + 1];
            for (int i = rows; i >= 0; i--) {
                for (int j = cols; j >= 0; j--) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            dp[rows - 1][cols] = 0;

            for (int i = rows - 1; i >= 0; i--) {
                for (int j = cols - 1; j >= 0; j--) {
                    dp[i][j] = grid[i][j] + min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
            return dp[0][0];
        }

        /*
        Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
        Output: 4
        Explanation: The longest increasing path is [1, 2, 6, 9].
         */
        class RowCol {
            int row;
            int col;

            RowCol() {
            }

            RowCol(int row, int col) {
                this.row = row;
                this.col = col;
            }

            @Override
            public boolean equals(Object o) {
                if (o == this) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RowCol rowCol = (RowCol) o;
                return row == rowCol.row && col == rowCol.col;
            }

            @Override
            public int hashCode() {
                return Objects.hash(row, col);
            }
        }

        public int dfs(RowCol cell, int[][] matrix, Map<RowCol, Integer> memo, List<RowCol> directions, int rows, int cols) {
            if (memo.containsKey(cell)) {
                return memo.get(cell);
            }

            int maxLen = 1;
            for (RowCol dir : directions) {
                int nextRow = cell.row + dir.row;
                int nextCol = cell.col + dir.col;

                if (0 <= nextRow && nextRow < rows &&
                        0 <= nextCol && nextCol < cols &&
                        matrix[nextRow][nextCol] > matrix[cell.row][cell.col]) {

                    maxLen = max(maxLen, 1 + dfs(new RowCol(nextRow, nextCol), matrix, memo, directions, rows, cols));
                }
            }

            memo.put(cell, maxLen);
            return maxLen;
        }

        public int longestIncreasingPath(int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            Map<RowCol, Integer> memo = new HashMap<>();

            List<RowCol> directions = new ArrayList<>();
            directions.add(new RowCol(0, 1));
            directions.add(new RowCol(1, 0));
            directions.add(new RowCol(0, -1));
            directions.add(new RowCol(-1, 0));

            int maxLen = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    maxLen = max(maxLen, dfs(new RowCol(i, j), matrix, memo, directions, rows, cols));
                }
            }
            return maxLen;
        }

        public static void main(String[] args) {
            DynamicProgramming dp = new DynamicProgramming();
            //System.out.println(dp.rob(new int[]{2, 3, 2}));
            //System.out.println(dp.canPartition(new int[]{1, 5, 11, 5}));
            //System.out.println(dp.coinChange(new int[]{1, 2, 5}, 11));
            //System.out.println(dp.lengthOfLIS1(new int[]{10,9,2,5,3,7,101,18}));
            //System.out.println(dp.longestCommonSubsequence("abcde", "ace"));
            //System.out.println(dp.wordBreak("applepenapple", List.of("apple","pen")));
            //System.out.println(dp.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
            System.out.println(dp.longestIncreasingPath(new int[][]{{9,9,4}, {6,6,8},{2,1,1}}));
        }
    }

    static class Geometry {
        public int reverse(int x) {
            int rev = 0;
            int sign = x < 0? -1: 1;
            x = abs(x);
            while (x != 0) {
                int rem = x % 10;
                rev = (rev * 10) + rem;
                x = x / 10;
            }
            return sign * rev;
        }

        public int maxPoints(int[][] points) {
            int rows = points.length, cols = points[0].length;
            int maxPoints = 1;
            for (int i = 0; i < rows; i++) {
                Map<Integer, Integer> count = new HashMap<>();
                int slope;
                for (int j = i + 1; j < rows; j++) {
                    if (points[i][0] == points[j][0]) {
                        slope = Integer.MAX_VALUE;
                    } else {
                        slope = (points[j][1] - points[i][1]) /(points[j][0] - points[i][0]);
                    }
                    count.put(slope, count.getOrDefault(slope, 0) + 1);
                    maxPoints = max(maxPoints, 1 + count.get(slope));
                }
            }
            return maxPoints;
        }


        public static void main(String[] args) {
            Geometry geometry = new Geometry();
            //System.out.println(geometry.reverse(-123));
            System.out.println(geometry.maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        }
    }

    static class Graph {
        /*
    Input: grid = [
          ["1","1","1","1","0"],
          ["1","1","0","1","0"],
          ["1","1","0","0","0"],
          ["0","0","0","0","0"]
        ]
    Output: 1
     */

        public void dfs(int i, int j, int[][] directions, char[][] grid, int rows, int cols) {
            grid[i][j] = '0';
            for (int[] direction: directions) {
                int nr = i + direction[0], nc = j + direction[1];
                if (0 <= nr && nr < rows && 0 <= nc && nc < cols && grid[nr][nc] == '1') {
                    dfs(nr, nc, directions, grid, rows, cols);
                }
            }
        }

        public int numIslands(char[][] grid) {
            int rows = grid.length, cols = grid[0].length;
            int count = 0;
            int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        dfs(i, j, directions, grid, rows, cols);
                        count += 1;
                    }
                }
            }
            return count;
        }

        static class Node {
            public int val;
            public List<Node> neighbors;
            public Node() {
                val = 0;
                neighbors = new ArrayList<Node>();
            }
            public Node(int _val) {
                val = _val;
                neighbors = new ArrayList<Node>();
            }
            public Node(int _val, ArrayList<Node> _neighbors) {
                val = _val;
                neighbors = _neighbors;
            }
        }

        public Node dfs(Map<Node, Node> visited, Node node) {
            if (visited.containsKey(node)) {
                return visited.get(node);
            }
            Node clone = new Node(node.val);
            visited.put(node, clone);
            for (Node neighbor: node.neighbors) {
                clone.neighbors.add(dfs(visited, neighbor));
            }
            return clone;
        }

        public Node cloneGraph(Node node) {
            Map<Node, Node> visited = new HashMap<>();
            return dfs(visited, node);
        }

        public boolean isBipartite(int[][] graph) {
            int[] color = new int[graph.length];
            Arrays.fill(color, -1);
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < graph.length; i++) {
                if (color[i] == -1) {
                    color[i] = 0;
                    queue.add(i);
                    while (!queue.isEmpty()) {
                        int node = queue.poll();
                        for (int neighbor: graph[node]) {
                            if (color[neighbor] == -1) {
                                color[neighbor] = 1 - color[node];
                                queue.add(neighbor);
                            } else if (color[neighbor] == color[node]) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }

        class OrangeRot {
            int row;
            int col;
            int minutes;

            OrangeRot(int row, int col, int minutes) {
                this.row = row;
                this.col = col;
                this.minutes = minutes;
            }
        }

        public int orangesRotting(int[][] grid) {
            int rows = grid.length, cols = grid[0].length;
            Queue<OrangeRot> queue = new LinkedList<>();
            int freshCount = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        freshCount++;
                    } else if (grid[i][j] == 2) {
                        queue.add(new OrangeRot(i, j, 0));
                    }
                }
            }

            int minutes = 0;
            int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            while (!queue.isEmpty()) {
                OrangeRot orangeRot = queue.poll();
                int r = orangeRot.row, c = orangeRot.col;
                for (int[] direction: directions) {
                    int dr = direction[0], dc = direction[1];
                    int nr = r + dr, nc = c + dc;
                    if (0 <= nr && nr < rows && 0 <= nc && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 0;
                        int nextMinute = orangeRot.minutes + 1;
                        freshCount -= 1;
                        queue.add(new OrangeRot(nr, nc, nextMinute));
                        minutes = nextMinute;
                    }
                }
            }
            return freshCount == 0? minutes: -1;
        }

        /*
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        Output: 5
        Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
         */

        static class WordCount {
            String word;
            int count;
            WordCount(String word, int count) {
                this.word = word;
                this.count = count;
            }
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) {
                return 0; // per problem definition
            }

            // Build pattern → words mapping
            Map<String, List<String>> patternMap = new HashMap<>();
            wordSet.add(beginWord);

            for (String word : wordSet) {
                for (int i = 0; i < word.length(); i++) {
                    String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                    patternMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
                }
            }

            // BFS
            Queue<WordCount> queue = new LinkedList<>();
            queue.add(new WordCount(beginWord, 1));
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);

            while (!queue.isEmpty()) {
                WordCount wc = queue.poll();
                String word = wc.word;
                int count = wc.count;

                for (int i = 0; i < word.length(); i++) {
                    String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                    for (String neighbor : patternMap.getOrDefault(pattern, Collections.emptyList())) {
                        if (endWord.equals(neighbor)) {
                            return count + 1;
                        }
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(new WordCount(neighbor, count + 1));
                        }
                    }
                }
            }

            return 0;
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] indegree = new int[numCourses];
            List<Integer> res = new ArrayList<>();
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Queue<Integer> queue = new LinkedList<>();

            for(int[] prerequisite: prerequisites) {
                int src = prerequisite[1], dst = prerequisite[0];
                indegree[dst] += 1;
                graph.computeIfAbsent(src, k -> new ArrayList<>()).add(dst);
            }

            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int course = queue.poll();
                res.add(course);
                for (int neighbor: graph.getOrDefault(course, new ArrayList<>())) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
            return res.stream().mapToInt(Integer::intValue).toArray();
        }

        /*
        Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
        Output: 200
        Explanation:
        The graph is shown above.
        The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
         */
        static class BellmanFordAlgorithm {
            public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
                int[] prices = new int[n];
                Arrays.fill(prices, Integer.MAX_VALUE);
                prices[src] = 0;
                for (int i = 0; i < k + 1; i++) {
                    int[] temp = Arrays.copyOf(prices, n);
                    for (int[] flight: flights) {
                        int u = flight[0], v = flight[1], price = flight[2];
                        if (prices[u] == Integer.MAX_VALUE) {
                            continue;
                        }
                        int cost = prices[u] + price;
                        if (cost < temp[v]) {
                            temp[v] = cost;
                        }
                    }
                    prices = temp;
                }
                return prices[dst];
            }

            public static void main(String[] args) {
                BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm();
                System.out.println(
                        bellmanFordAlgorithm.findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 1));
            }
        }


        public static void main(String[] args) {
            Graph graph = new Graph();
//            System.out.println(graph.numIslands(new char[][]{
//                    {'1','1','1','1','0'},
//                    {'1','1','0','1','0'},
//                    {'1','1','0','0','0'},
//                    {'0','0','0','0','0'}
//                }));
//            Node n1 = buildGraph();
//            // Clone the graph
//            Node cloned = new Graph().cloneGraph(n1);
//
//            // Print adjacency list of original and cloned
//            System.out.println("Original Graph:");
//            printGraph(n1, new HashSet<>());
//
//            System.out.println("\nCloned Graph:");
//            printGraph(cloned, new HashSet<>());
            //System.out.println(graph.isBipartite(new int[][]{{1,3},{0,2},{1,3},{0,2}}));
            //System.out.println(graph.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
            //System.out.println(graph.ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));
            System.out.println(Arrays.toString(graph.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        }

        public static Node buildGraph() {
            // Build graph manually
            Node n1 = new Node(1);
            Node n2 = new Node(2);
            Node n3 = new Node(3);
            Node n4 = new Node(4);

            n1.neighbors.add(n2);
            n1.neighbors.add(n4);

            n2.neighbors.add(n1);
            n2.neighbors.add(n3);

            n3.neighbors.add(n2);
            n3.neighbors.add(n4);

            n4.neighbors.add(n1);
            n4.neighbors.add(n3);

           return n1;
        }

        // Utility to print graph
        static void printGraph(Node node, Set<Node> visited) {
            if (node == null || visited.contains(node)) return;
            visited.add(node);

            System.out.print("Node " + node.val + " -> ");
            for (Node neigh : node.neighbors) {
                System.out.print(neigh.val + " ");
            }
            System.out.println();

            for (Node neigh : node.neighbors) {
                printGraph(neigh, visited);
            }
        }

    }


    static class Tries {
        static class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEnd;
            TrieNode () {
                children = new HashMap<>();
                isEnd = false;
            }
        }
        static class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void insert(String word) {
                TrieNode node = root;
                for (Character c: word.toCharArray()) {
                    if (!node.children.containsKey(c)) {
                        node.children.put(c, new TrieNode());
                    }
                    node = node.children.get(c);
                }
                node.isEnd = true;
            }

            public boolean search(String word) {
                TrieNode node = this.contains(word);
                return node != null && node.isEnd;
            }

            public boolean startsWith(String prefix) {
                TrieNode node = this.contains(prefix);
                return node != null;
            }

            public TrieNode contains(String word) {
                TrieNode node = root;
                for (Character c: word.toCharArray()) {
                    if (!node.children.containsKey(c)) {
                        return null;
                    }
                    node = node.children.get(c);
                }
                return node;
            }
        }

        public static void main(String[] args) {
            /**
             * Your Trie object will be instantiated and called as such:
             **/
             Trie obj = new Trie();
             obj.insert("Dharani");
             boolean param_2 = obj.search("Dharani");
             boolean param_3 = obj.startsWith("Dhara");
             System.out.println(param_2);
             System.out.println(param_3);
        }
    }



    public class Blind75 {
        public static void main(String[] args) {

        }
    }
}
