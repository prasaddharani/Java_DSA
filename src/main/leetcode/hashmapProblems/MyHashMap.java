package main.leetcode.hashmapProblems;

public class MyHashMap {

    // Definition for the linked list node used in separate chaining.
    private static class ListNode {
        int key;
        int value;
        ListNode next;

        public ListNode() {
            // Default constructor
        }

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private ListNode[] hashMap;
    private int capacity = 1000; // Same as Python version's length

    public MyHashMap() {
        hashMap = new ListNode[capacity];
        // Initialize each bucket with a dummy node.
        for (int i = 0; i < capacity; i++) {
            hashMap[i] = new ListNode(-1, -1);
        }
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void put(int key, int value) {
        int index = hash(key);
        ListNode cur = hashMap[index];
        // Traverse the chain to see if the key exists.
        while (cur.next != null) {
            if (cur.next.key == key) {
                cur.next.value = value;
                return;
            }
            cur = cur.next;
        }
        // Append a new node at the end.
        cur.next = new ListNode(key, value);
    }

    public int get(int key) {
        int index = hash(key);
        ListNode cur = hashMap[index].next;
        while (cur != null) {
            if (cur.key == key) {
                return cur.value;
            }
            cur = cur.next;
        }
        return -1;
    }

    public void remove(int key) {
        int index = hash(key);
        ListNode cur = hashMap[index];
        while (cur != null && cur.next != null) {
            if (cur.next.key == key) {
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }

    // Main method to test the implementation.
    public static void main(String[] args) {
        MyHashMap solution = new MyHashMap();
        solution.put(1, 1);
        solution.put(2, 2);
        System.out.println("Get key 1: " + solution.get(1)); // Expected output: 1
        System.out.println("Get key 3: " + solution.get(3)); // Expected output: -1
        solution.put(2, 1);
        System.out.println("Get key 2: " + solution.get(2)); // Expected output: 1
        solution.remove(2);
        System.out.println("Get key 2 after removal: " + solution.get(2)); // Expected output: -1
    }
}