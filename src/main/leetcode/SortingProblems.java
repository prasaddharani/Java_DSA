package main.leetcode;

import java.util.*;

public class SortingProblems {

    public static void main(String[] args) {

    }

    /*
    Bucket Sort
     */

    /*
    692. Top K Frequent Words
     */

    /*
    Input: words = ["i","love","leetcode","i","love","coding"], k = 2
    Output: ["i","love"]
    Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
     */

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordsCount = new HashMap<>();
        for (String word : words) {
            wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1);
        }

        // Initialize buckets with empty lists
        List<List<String>> buckets = new ArrayList<>();
        for (int i = 0; i <= words.length; i++) {
            buckets.add(new ArrayList<>());
        }

        // Populate buckets based on word frequencies
        for (Map.Entry<String, Integer> word : wordsCount.entrySet()) {
            buckets.get(word.getValue()).add(word.getKey());
        }

        // Collect the top K frequent words
        List<String> res = new ArrayList<>();
        for (int i = words.length; i > 0; i--) {
            if (!buckets.get(i).isEmpty()) {
                Collections.sort(buckets.get(i)); // Sort alphabetically
                for (String word : buckets.get(i)) {
                    res.add(word);
                    if (res.size() == k) {
                        return res;
                    }
                }
            }
        }
        return res;
    }



    /* Recursions */

    /*
    21. Merge Two Sorted Lists
     */

    /*
    Input: list1 = [1,2,4], list2 = [1,3,4]
    Output: [1,1,2,3,4,4]
     */

    /**
     * Definition for singly-linked list. **/
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2; // Return list2 if list1 is null
        }
        if (list2 == null) {
            return list1; // Return list1 if list2 is null
        }
        if (list1.val > list2.val) {
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }
        list1.next = mergeTwoLists(list1.next, list2);
        return list1;
    }

    public ListNode mergeTwoListsWithoutRecursion(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        }

        if (list2 != null) {
            curr.next = list2;
        }
        return dummy.next;
    }

    /*
    394. Decode String

    Input: s = "3[a]2[bc]"
    Output: "aaabcbc"
     */

    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ']') {
                stack.push(String.valueOf(c));
            } else {
                // Build the substring within the brackets.
                StringBuilder substr = new StringBuilder();
                while (!stack.isEmpty() && !stack.peek().equals("[")) {
                    substr.insert(0, stack.pop());
                }
                // Remove the '['.
                stack.pop();

                // Build the repeat count.
                StringBuilder k = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
                    k.insert(0, stack.pop());
                }
                int count = Integer.parseInt(k.toString());

                // Append the substring count times.
                StringBuilder decoded = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    decoded.append(substr);
                }
                stack.push(decoded.toString());
            }
        }

        // Join the elements in the stack to form the final result.
        StringBuilder result = new StringBuilder();
        for (String str : stack) {
            result.append(str);
        }
        return result.toString();
    }


    /* Merge Sort */

    /*
    148. Sort List

    Input: head = [4,2,1,3]
    Output: [1,2,3,4]
     */

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode left = head;
        ListNode right = getMid(head);
        ListNode temp = right.next;
        right.next = null;
        right = temp;
        left = sortList(left);
        right = sortList(right);
        return mergeLists(left, right);
    }

    public ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast !=  null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode mergeLists(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }

        if (left != null) {
            curr.next = left;
        }

        if (right != null) {
            curr.next = right;
        }

        return dummy.next;
    }

    /* Dutch National Flag algorithm */

    /*
    75. Sort Colors
     */

    /*
    Input: nums = [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]
     */

    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

    /* Quick Select Sort */

    /*
    215. Kth Largest Element in an Array
     */

    /*
    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5
     */

    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    public int quickSelect(int[] nums, int left, int right, int k) {
        int pivot = nums[right];
        int p = left;
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }
        int temp = nums[right];
        nums[right] = nums[p];
        nums[p] = temp;
        if (p == k) {
            return nums[p];
        } else if (p > k) {
            return quickSelect(nums, left, p - 1, k);
        } else {
            return quickSelect(nums, p + 1, right, k);
        }
    }

    public int findKthLargest1(int[] nums, int k) {
            k = nums.length - k;  // Adjust k for zero-indexing
            return quickSelect1(nums, 0, nums.length - 1, k);
    }

    private int quickSelect1(int[] nums, int left, int right, int k) {
        int pivot = nums[right];
        int low = left;
        int high = right;

        while(low <= high) {
            while(low <= high && nums[low] < pivot) {
                low++;
            }
            while(low <= high && nums[high] > pivot) {
                high--;
            }
            if(low <= high) {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
                low++;
                high--;
            }
        }

        if(k <= high) {
            return quickSelect1(nums, left, high, k);
        } else if(k >= low) {
            return quickSelect1(nums, low, right, k);
        } else {
            return nums[k];
        }
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>((a,b)->b-a);
        int val=0;
        for(int num:nums){
            maxHeap.add(num);
        }
        for(int i=1;i<=k;i++){
            val=maxHeap.remove();
        }
        return val;
    }
}
