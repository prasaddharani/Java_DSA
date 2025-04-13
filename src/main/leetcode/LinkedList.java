package main.leetcode;

public class LinkedList {
    public static void main(String[] args) {

    }

    /**
     * Definition for singly-linked list. **/
     public class ListNode {
         int val;
         ListNode next;
         ListNode(int val, ListNode next) {
             this.val = val;
             this.next = next;
         }

        ListNode(int x) {
            val = x;
            next = null;
        }
     }


    /*
    160. Intersection of Two Linked Lists

    Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
    Output: Intersected at '8'
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = l1 != null ? l1.next: headB;
            l2 = l2 != null ? l2.next: headA;
        }
        return l1;
    }

    /*
    19. Remove Nth Node From End of List

    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = dummy;
        // Move fast pointer n + 1 steps ahead to reach one node before target
        for (int i = 0; i < n + 1; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return head; // n is larger than the list length
            }
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the nth node
        if (slow != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

    /*
    24. Swap Nodes in Pairs

    Input: head = [1,2,3,4]
    Output: [2,1,4,3]
     */

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);  // Dummy node simplifies head swaps
        ListNode prev = dummy;                   // Points to the node before the current pair
        ListNode cur = dummy.next;               // Current node we're working on (head initially)

        while (cur != null && cur.next != null) {
            ListNode first = cur;               // First node in the pair
            ListNode second = cur.next;         // Second node in the pair

            // --- Swap the nodes ---
            prev.next = second;                 // Point previous node to the second node
            first.next = second.next;           // First now points to the node after the pair
            second.next = first;                // Second now points to first (swap done)

            // --- Move pointers for next iteration ---
            prev = first;                       // 'first' is now the previous node for the next pair
            cur = first.next;                   // Move to the next pair
        }

        return dummy.next;  // Return the new head of the list
    }

    /*
    61. Rotate List

    Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]
     */


    public ListNode rotateRight(ListNode head, int k) {
        // Edge case: empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode tail = head;
        int length = 1;

        // Step 1: Find the tail and the length of the list
        while (tail.next != null) {
            tail = tail.next;
            length += 1;
        }

        // Step 2: Normalize k to avoid extra rotations
        k = k % length;
        if (k == 0) {
            return head;  // No rotation needed
        }

        // Step 3: Find the new tail node (length - k - 1 steps from head)
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        // Step 4: The new head will be the next node
        ListNode newHead = newTail.next;

        // Step 5: Break the link and reconnect the tail to the old head
        newTail.next = null;
        tail.next = head;

        // Step 6: Return the new head
        return newHead;
    }


    /*
    2. Add Two Numbers

    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;  // To store carry between digit additions

        // Dummy node to simplify handling the result list
        ListNode dummy = new ListNode(0, null);
        ListNode cur = dummy;  // Pointer to build the result list

        // Loop continues as long as there's a node in l1 or l2 or there's a carry
        while (l1 != null || l2 != null || carry != 0) {
            // Get values from current nodes or 0 if we've reached the end
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            // Add the two digits and the carry
            int valSum = val1 + val2 + carry;

            // Update carry for next iteration
            carry = valSum / 10;

            // The new digit to store in the result
            valSum = valSum % 10;

            // Add new node with the digit to the result list
            cur.next = new ListNode(valSum, null);

            // Move to next position in result
            cur = cur.next;

            // Advance l1 and l2 if possible
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        // Return the next node after dummy (which is the head of the result list)
        return dummy.next;
    }

    // Slow and Fast Pointer Problems
    /*
    876. Middle of the Linked List

    Input: head = [1,2,3,4,5]
    Output: [3,4,5]
    Explanation: The middle node of the list is node 3.
     */

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
    142. Linked List Cycle II

    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to the second node.
     */

    public ListNode detectCycle(ListNode head) {
        // Initialize two pointers for the Floyd's Cycle Detection algorithm
        ListNode slow = head, fast = head;

        // Step 1: Detect if a cycle exists using two pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow pointer by 1
            fast = fast.next.next;    // Move fast pointer by 2

            if (slow == fast) {       // If they meet, a cycle exists
                break;
            }
        }

        // Step 2: If no cycle is detected, return null
        if (fast == null || fast.next == null) return null;

        // Step 3: Find the start node of the cycle
        // Move one pointer to the head, keep the other at meeting point
        fast = head;
        while (slow != fast) {
            slow = slow.next;         // Move both pointers one step at a time
            fast = fast.next;
        }

        // Step 4: Both pointers now meet at the start of the cycle
        return slow;
    }

}
