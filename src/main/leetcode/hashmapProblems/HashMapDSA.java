package main.leetcode.hashmapProblems;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashMapDSA {

    public static void main(String[] args) {
        //System.out.println(maxNumberOfBalloons("loonbalxballpoon"));
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }

    /*
    1189. Maximum Number of Balloons

    Input: text = "loonbalxballpoon"
    Output: 2
     */

    public static int maxNumberOfBalloons(String text) {

        String balloon = "balloon";
        Map<Character, Integer> balloonMap = new HashMap<>();
        for (Character c: balloon.toCharArray()) {
            balloonMap.put(c, 1 + balloonMap.getOrDefault(c, 0));
        }

        Map<Character, Integer> textMap = new HashMap<>();
        for (Character c: text.toCharArray()) {
            textMap.put(c, 1 + textMap.getOrDefault(c, 0));
        }
        int minNumber = text.length();
        for (Character c: balloon.toCharArray()) {
            minNumber = Math.min(minNumber, textMap.getOrDefault(c, 0) / balloonMap.get(c));
        }
        return minNumber;
    }

    /*
    1512. Number of Good Pairs

    Input: nums = [1,2,3,1,1,3]
    1 -> 1
    3 -> 1
    1 -> 2 res = 1
    1 -> 3 res = 1 + 2 = 3
    3 -> 2 res = 1 + (3) = 4

    Output: 4
    Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
     */

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int goodPairs = 0;
        for (Integer num: nums) {
            if (hashMap.containsKey(num)) {
                goodPairs += hashMap.get(num);
                hashMap.put(num, hashMap.get(num) + 1);
            } else {
                hashMap.put(num, 1);
            }
        }
        return goodPairs;
    }

    /*
    205. Isomorphic Strings
    Input: s = "egg", t = "add"
    Output: true
     */

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character sCharacter = s.charAt(i);
            Character tCharacter = t.charAt(i);

            if (sMap.containsKey(sCharacter) && sMap.get(sCharacter) != tCharacter) {
                return false;
            } else {
                sMap.put(sCharacter, tCharacter);
            }

            if (tMap.containsKey(tCharacter) && tMap.get(tCharacter) != sCharacter) {
                return false;
            } else {
                tMap.put(tCharacter, sCharacter);
            }
        }
        return true;
    }

    /*
    383. Ransom Note
    Input: ransomNote = "aa", magazine = "aab"
    Output: true
     */

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> ransomMap = new HashMap<>();
        Map<Character, Integer> magazineMap = new HashMap<>();
        for (Character c: ransomNote.toCharArray()) {
            ransomMap.put(c, 1 + ransomMap.getOrDefault(c, 0));
        }

        for (Character m: magazine.toCharArray()) {
            magazineMap.put(m, 1 + magazineMap.getOrDefault(m, 0));
        }

        for (Map.Entry<Character, Integer> entry: ransomMap.entrySet()) {
            if (magazineMap.containsKey(entry.getKey()) && magazineMap.get(entry.getKey()) >= entry.getValue()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /*
    219. Contains Duplicate II

    Input: nums = [1,2,3,1], k = 3
    Output: true
     */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(i - k) && hashMap.get(i - k) <= k) {
                return true;
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return false;
    }

    /*
    49. Group Anagrams

    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
            Map<String, List<String>> anagrams = new HashMap<>();
            for (String s : strs) {
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                String key = String.valueOf(chars);
                anagrams.computeIfAbsent(key, (k) -> new ArrayList<>()).add(s);
            }
            return new ArrayList<List<String>>(anagrams.values());
        }


    /*
    128. Longest Consecutive Sequence

    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9
     */

    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        Set<Integer> numSet = new HashSet<>();
        for (Integer num: nums) {
            numSet.add(num);
        }
        for (Integer num: numSet) {
            int length = 0;
            if (!numSet.contains(num - 1)) {
                while (numSet.contains(num + length)) {
                    length++;
                }
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    /*
    767. Reorganize String

    Example 1:
        Input: s = "aab"
        Output: "aba"
    Example 2:
        Input: s = "aaab"
        Output: ""
     */

    public String reorganizeString(String s) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for (Character c: s.toCharArray()) {
            hashMap.put(c, 1 + hashMap.getOrDefault(c, 0));
        }
        StringBuilder res = new StringBuilder();
        PriorityQueue<Map.Entry<Integer, Character>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getKey() - a.getKey() // Compare by value in descending order
        );
        for (Map.Entry<Character, Integer> entry: hashMap.entrySet()) {
            Map.Entry<Integer, Character> temp = Map.entry(entry.getValue(), entry.getKey());
            maxHeap.add(temp);
        }
        Map.Entry<Integer, Character> prev = null;
        while (!maxHeap.isEmpty() || prev != null) {
            if (prev != null && maxHeap.isEmpty()) {
                return "";
            }
            Map.Entry<Integer, Character> pop = maxHeap.poll();
            res.append(pop.getValue());
            Map.Entry<Integer, Character> temp = Map.entry(pop.getKey() - 1, pop.getValue());
            if (prev != null) {
                maxHeap.add(prev);
            }
            if (temp.getKey() != 0) {
                prev = temp;
            }
        }
        return res.toString();
    }


    public String reorganizeString1(String s) {
            // Count each character's frequency.
            Map<Character, Integer> counter = new HashMap<>();
            for (char c : s.toCharArray()) {
                counter.put(c, counter.getOrDefault(c, 0) + 1);
            }

            // Create a max heap (PriorityQueue) that orders by frequency.
            PriorityQueue<CharCount> maxHeap = new PriorityQueue<>((a, b) -> b.count - a.count);
            for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
                maxHeap.offer(new CharCount(entry.getKey(), entry.getValue()));
            }

            StringBuilder res = new StringBuilder();
            CharCount prev = null;

            // Process characters from the heap.
            while (!maxHeap.isEmpty() || prev != null) {
                // If we have a pending previous character but the heap is empty, it's not possible to reorganize.
                if (prev != null && maxHeap.isEmpty()) {
                    return "";
                }

                CharCount current = maxHeap.poll();
                res.append(current.c);
                current.count--;

                // If we have a previous element waiting, push it back after using its gap.
                if (prev != null) {
                    maxHeap.offer(prev);
                    prev = null;
                }

                // If the current character still has a remaining count, store it as previous.
                if (current.count > 0) {
                    prev = current;
                }
                // For debugging, you can print the current state:
                // System.out.println("Prev: " + (prev != null ? prev.c + ":" + prev.count : "null") + " Heap: " + maxHeap);
            }

            return res.toString();
        }

   // Helper class to hold character and its remaining count.
    private static class CharCount {
            char c;
            int count;

            public CharCount(char c, int count) {
                this.c = c;
                this.count = count;
            }

            @Override
            public String toString() {
                return c + ":" + count;
            }
        }

     /*
     659. Split Array into Consecutive Subsequences
     Input : [1, 2, 3, 3, 4, 5] output: True ([1, 2, 3], [3, 4, 5]
      */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();

        // Build frequency counter.
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Process each number in the array.
        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            }
            if (need.getOrDefault(num, 0) > 0) {
                // Use the number to complete a subsequence.
                need.put(num, need.get(num) - 1);
                need.put(num + 1, need.getOrDefault(num + 1, 0) + 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                // Create a new subsequence: use num, num+1, num+2.
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
            freq.put(num, freq.get(num) - 1);
        }
        return true;
    }
}
