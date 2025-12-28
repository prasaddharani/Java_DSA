package org.example.repractice.leetcode;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Math.*;

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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
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
        } else {
            cur.next = l2;
        }
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        int interval = 1;
        while (interval < lists.length) {
//            System.out.println("Interval: " + interval);
//            System.out.println("ListNode length: " + lists.length);
            for (int i = 0; i + interval < lists.length; i+= interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
//            int i = 1;
//            for (ListNode list: lists) {
//                printLinkedList(list);
//                System.out.println("done " + i);
//                i++;
//            }
            interval *= 2;
        }
        return lists[0];
    }

    public static void main(String[] args) {
        ListNode res = removeNthFromEnd(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
        ListNode res1 = swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
        ListNode res2 = addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))),
                new ListNode(5, new ListNode(6, new ListNode(4))));
        ListNode res3 = reverseKGroup(
                 new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
        ListNode res4 = mergeKLists(new ListNode[]{
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(2, new ListNode(6))
        });
        printLinkedList(res4);
    }
}

class StackProblems {

    /*
    Input: s = "()[]{}"
    Output: true
     */

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c: s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if ((!stack.isEmpty()) &&
                        ((c == '}' && stack.peek() == '{')
                        || (c == ']' && stack.peek() == '[')
                        || (c == ')' && stack.peek() == '('))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int val) {
            stack.add(val);
            while (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.add(val);
            }
        }

        public void pop() {
            int val = stack.removeLast();
            if (!minStack.isEmpty() && minStack.peek() == val) {
                minStack.removeLast();
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
        Integer height;
        Integer index;

        Histogram(Integer height, Integer index) {
            this.height = height;
            this.index = index;
        }
    }

    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Histogram> stack = new Stack<>();
        for (int start = 0; start < heights.length; start++) {
            int index = start;
            while (!stack.isEmpty() && stack.peek().height > heights[start]) {
                Histogram histogram = stack.pop();
                index = histogram.index;
                maxArea = max(maxArea, ((start - index) * histogram.height));
            }
            stack.push(new Histogram(heights[start], index));
        }

        for (Histogram histogram: stack) {
            maxArea = max(maxArea, ((heights.length) - histogram.index) * histogram.height);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        //System.out.println(isValid("()[]{}"));
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
}

class QueueProblems {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            // 1️⃣ Remove indices outside the window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2️⃣ Maintain decreasing order
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3️⃣ Add current index
            deque.addLast(i);

            // 4️⃣ Record result when window is valid
            if (i >= k - 1) {
                res[index++] = nums[deque.peekFirst()];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}

class DutchAlg {

    public static int[] sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid < high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
                mid++;
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortColors(new int[]{2, 0, 2, 1, 1, 0})));
    }
}

class BinarySearchProblems {

    public static int[] searchRange(int[] nums, int target) {
        int left = binarySearchHelper(nums, target, true);
        int right = binarySearchHelper(nums, target, false);
        return new int[]{left, right};
    }

    public static int binarySearchHelper(int[] nums, int target, boolean leftBias) {
        int l = 0;
        int r = nums.length - 1;
        int res = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l++;
            } else if (nums[mid] > target) {
                r--;
            } else {
               res = mid;
               if (leftBias) {
                   r--;
               } else {
                   l++;
               }
            }
        }
        return res;
    }

    public static int search(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }

    public static int binarySearchHelper(int[] nums, int target, int l, int r) {
        if (l > r) {
            return -1;
        }

        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] > nums[l]) {
            if (target >= nums[l] && target < nums[mid]) {
                return binarySearchHelper(nums, target, l, mid -1);
            } else {
                return binarySearchHelper(nums, target, mid + 1, r);
            }
        } else {
            if (target <= nums[r] && target > nums[mid]) {
                return binarySearchHelper(nums, target, mid + 1, r);
            } else {
                return binarySearchHelper(nums, target, l, mid -1);
            }
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int total = nums1.length + nums2.length;

        int x = nums1.length;
        int y = nums2.length;

        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = partitionX == 0? Integer.MIN_VALUE: nums1[partitionX - 1];
            int minRightX = partitionX == x? Integer.MAX_VALUE: nums1[partitionX];

            int maxLeftY = partitionY == 0? Integer.MIN_VALUE: nums2[partitionY - 1];
            int minRightY = partitionY == y? Integer.MAX_VALUE: nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if (total % 2 == 0) {
                    return (double) (max(maxLeftX, maxLeftY) + min(minRightX, minRightY)) / 2;
                } else {
                    return max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        //System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}

class Backtrack {

    private static void backtrack(List<List<Integer>> res, boolean[] used, Stack<Integer> path, int[] nums) {
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
            backtrack(res, used, path, nums);
            used[i] = false;
            path.pop();
        }
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new boolean[nums.length], new Stack<>(), nums);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, int[] nums, int start, Stack<Integer> path) {
        res.add(new ArrayList<>(path));

        for (int end = start; end < nums.length; end++) {
            path.add(nums[end]);
            backtrack(res, nums, end + 1, path);
            path.pop();
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, nums, 0, new Stack<>());
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(permute(new int[]{1, 2, 3}));
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}

class CalendarProblem {

    static class CalendarNode {
        int start;
        int end;
        CalendarNode left;
        CalendarNode right;

        CalendarNode(int start, int end, CalendarNode left, CalendarNode right) {
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
        }

        CalendarNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
        }

        boolean insert(int start, int end) {
            CalendarNode cur = this;
            while (cur != null) {
                if (cur.start >= end) {
                    if (cur.left == null) {
                        cur.left = new CalendarNode(start, end);
                        return true;
                    }
                    cur = cur.left;
                } else if (cur.end <= start) {
                    if (cur.right == null) {
                        cur.right = new CalendarNode(start, end);
                        return true;
                    }
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    static class MyCalendar {
        private CalendarNode root;

        public MyCalendar() {
            this.root = null;
        }

        public boolean book(int startTime, int endTime) {
            if (root == null) {
                root = new CalendarNode(startTime, endTime);
                return true;
            }
            return root.insert(startTime, endTime);
        }
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20)); // return True
        System.out.println(myCalendar.book(15, 25)); // return False, It can not be booked because time 15 is already booked by another event.
        System.out.println(myCalendar.book(20, 30)); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
    }
}

class HeapProblems {

    static class MedianFinder {
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            minHeap.add(num);
            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                maxHeap.add(minHeap.poll());
            }

            if (maxHeap.size() > 1 + minHeap.size()) {
                minHeap.add(maxHeap.poll());
            }

            if (minHeap.size() > 1 + maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if (minHeap.size() > maxHeap.size()) {
                return minHeap.peek();
            }

            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }

            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        }
    }

    static class KFrequent {
        int key;
        int count;
        KFrequent (int key, int count) {
            this.key = key;
            this.count = count;
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[k];
        PriorityQueue<KFrequent> kFrequents = new PriorityQueue<>(Comparator.comparingInt(a -> a.count));
        for (Integer num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            kFrequents.add(new KFrequent(entry.getKey(), entry.getValue()));
            if (kFrequents.size() > k) {
                kFrequents.poll();
            }
        }
        int i = 0;
        while (!kFrequents.isEmpty()) {
            res[i++] = kFrequents.poll().key;
        }
        return res;
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0

        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}

class IntervalProblems {
    /*
    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
    */
    public static int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] current = intervals[0];
        res.add(current);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                current[1] = max(current[1], intervals[i][1]);
            } else {
                current = intervals[i];
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int res = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int prevVal = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevVal) {
                prevVal = intervals[i][1];
            } else {
                res++;
                prevVal = min(intervals[i][1], prevVal);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
    }
}

class SystemDesign {
    static class ListNode {
        private int key;
        private int val;
        private ListNode prev;
        private ListNode next;

        ListNode() {
            this.key = 0;
            this.val = 0;
            this.prev = null;
            this.next = null;
        }

        ListNode(int key, int val, ListNode prev, ListNode next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
    static class LRUCache {
        private Map<Integer, ListNode> cache;
        private int capacity;
        private ListNode head;
        private ListNode tail;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>();
            this.capacity = capacity;
            this.head = new ListNode();
            this.tail = new ListNode();
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        private void add(ListNode node) {
            node.prev = this.head;
            node.next = this.head.next;
            this.head.next.prev = node;
            this.head.next = node;
        }

        private void remove(ListNode node) {
            ListNode prevNode = node.prev;
            ListNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            node.prev = null;
            node.next = null;
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                ListNode node = cache.get(key);
                remove(node);
                add(node);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            ListNode node = null;
            if (cache.containsKey(key)) {
                node = cache.get(key);
                node.val = value;
                remove(node);
            } else {
                node = new ListNode(key, value);
            }
            cache.put(key, node);
            add(node);
            if (capacity < cache.size()) {
                ListNode lruNode = tail.prev;
                remove(lruNode);
                cache.remove(lruNode.key);
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

class GreedyProblems {

    /*
    Input: nums = [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */
    public static int jump(int[] nums) {
        int res = 0;
        int currentJumpEnd = 0;
        int maxJumps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxJumps = max(maxJumps, i + nums[i]);
            if (currentJumpEnd == i) {
                currentJumpEnd = maxJumps;
                res++;
            }
            if (currentJumpEnd > nums.length - 1) {
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
}

class DynamicProgramming {

    public static int rob(int[] nums) {
        int suffixExclusion = robHelper(Arrays.copyOfRange(nums, 0, nums.length - 2));
        int prefixExclusion = robHelper(Arrays.copyOfRange(nums, 1, nums.length - 1));
        return max(prefixExclusion, suffixExclusion);
    }
    public static int robHelper(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public static boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if (total % 2 != 0) {
            return false;
        }
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (Integer num: nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i - num] || dp[i];
            }
        }
        return dp[target];
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= amount; i++) {
            for (Integer coin: coins) {
                if (i >= coin) {
                    dp[i] = min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        for (Integer coin: coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount];
    }

    /*
    Input: nums = [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = max(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[nums.length - 1];
    }

    /*
    Input: text1 = "abcde", text2 = "ace"
    Output: 3
    Explanation: The longest common subsequence is "ace" and its length is 3.
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = max(dp[i-1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /*
    s = "applepenapple", wordDict = ["apple","pen"]
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        int maxLen = wordDict.stream().mapToInt(String::length).max().orElse(0);
        Set<String> wordSet = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= max(0, i - maxLen); j--) {
                if (wordSet.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static int minPathSum(int[][] grid) {
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

    static class RowCol {
        int row;
        int col;
        RowCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            RowCol rowCol = (RowCol) o;
            return row == rowCol.row && col == rowCol.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public static int dfs(RowCol rowCol, Map<RowCol, Integer> dp, int rows, int cols, int[][] matrix) {
        if (dp.containsKey(rowCol)) {
            return dp.get(rowCol);
        }
        int count = 1;
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        for (int[] direction: directions) {
            int dr = direction[0];
            int dc = direction[1];
            int nr = rowCol.row + dr;
            int nc = rowCol.col + dc;
            if (0 <= nr && nr < rows && 0 <= nc && nc < cols && matrix[nr][nc] > matrix[rowCol.row][rowCol.col]) {
                count = max(count, 1 + dfs(new RowCol(nr, nc), dp, rows, cols, matrix));
            }
        }
        dp.put(rowCol, count);
        return count;
    }

    public static int longestIncreasingPath(int[][] matrix) {
        Map<RowCol, Integer> dp = new HashMap<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxCount = max(maxCount, dfs(new RowCol(i, j), dp, rows, cols, matrix));
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        //System.out.println(rob(new int[]{2, 3, 2}));
        //System.out.println(canPartition(new int[]{1,5,11,5}));
        //System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        //System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        //System.out.println(longestCommonSubsequence("abcde", "ace"));
        //System.out.println(wordBreak("applepenapple", List.of("apple","pen")));
        //System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        System.out.println(longestIncreasingPath(new int[][]{{9,9,4}, {6,6,8},{2,1,1}}));
    }
}

class Geometry {
    public static int reverse(int x) {
        int sign = x < 0 ? -1: 1;
        x = abs(x);
        int rev = 0;
        while (x != 0) {
            int rem = x % 10;
            rev = (rev * 10) + rem;
            x = x/10;
        }
        return sign * rev;
    }

    /*
    Input: points = [[1,1],[2,2],[3,3]]
    Output: 3
     */
    public static int maxPoints(int[][] points) {
        int maxCount = 1;
        for (int i = 0; i < points.length; i++) {
            int[] p1 = points[i];
            Map<Integer, Integer> slope = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int[] p2 = points[j];
                int val = 0;
                if (p1[1] == p2[1]) {
                    val = Integer.MAX_VALUE;
                } else {
                    val = (p2[1] - p1[1]) / (p2[0] - p1[0]);
                }
                slope.put(val, slope.getOrDefault(val, 0) + 1);
                maxCount = max(maxCount, 1 + slope.get(val));
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        //System.out.println(reverse(-123));
        System.out.println(maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }
}

public class Blind75 {

}
