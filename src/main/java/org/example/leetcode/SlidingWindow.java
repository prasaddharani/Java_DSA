package org.example.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindow {
    public static void main(String[] args) {

        //System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println((minWindow("aa", "aa")));
    }

    /*
    Fixed Size
     */

    /*
    438. Find All Anagrams in a String

    Input: s = "cbaebabacd", p = "abc"
    Output: [0,6]
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
     */

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) {
            return res;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            pMap.put(p.charAt(i), pMap.getOrDefault(p.charAt(i), 0) + 1);
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        if (pMap.equals(sMap)) {
            res.add(0);
        }
        int left = 0;
        for (int right = p.length(); right < s.length(); right++) {
            sMap.put(s.charAt(right), sMap.getOrDefault(s.charAt(right), 0) + 1);
            sMap.put(s.charAt(left), sMap.get(s.charAt(left)) - 1);
            if (sMap.get(s.charAt(left)) == 0) {
                sMap.remove(s.charAt(left));
            }
            left++;
            if (pMap.equals(sMap)) {
                res.add(left);
            }
        }
        return res;
    }

    /*
    567. Permutation in String

    Input: s1 = "ab", s2 = "eidbaooo"
    Output: true
    Explanation: s2 contains one permutation of s1 ("ba").
     */

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
            s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0) + 1);
        }
        if (s1Map.equals(s2Map)) {
            return true;
        }
        int left = 0;
        for (int right = s1.length(); right < s2.length(); right++) {
            s2Map.put(s2.charAt(right), s2Map.getOrDefault(s2.charAt(right), 0) + 1);
            s2Map.put(s2.charAt(left), s2Map.get(s2.charAt(left)) - 1);
            if (s2Map.get(s2.charAt(left)) == 0) {
                s2Map.remove(s2.charAt(left));
            }
            left++;
            if (s1Map.equals(s2Map)) {
                return true;
            }
        }
        return false;
    }

    /*
        Dynamic Window Size
     */

    /*
       3. Longest Substring Without Repeating Characters

    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> sMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            while (sMap.containsKey(s.charAt(right))) {
                sMap.remove(s.charAt(left));
                left++;
            }
            sMap.put(s.charAt(right), 1);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }


    /*

    76. Minimum Window Substring

    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     */

    public static String minWindow(String s, String t) {

        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        for (Character c: t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int have = 0;
        int need = tMap.size();
        int resLen = Integer.MAX_VALUE;
        int left = 0;
        int[] res = new int[2];

        for (int right = 0; right < s.length() ; right++) {
            Character c = s.charAt(right);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            if (tMap.containsKey(c) && sMap.get(c).equals(tMap.get(c))) {
                have++;
            }
            while (have == need) {
                if (resLen > right - left + 1) {
                        resLen = right - left + 1;
                        res = new int[]{left , right};
                }
                Character leftChar = s.charAt(left);
                sMap.put(leftChar, sMap.get(leftChar) - 1);
                if (tMap.containsKey(leftChar) && tMap.get(leftChar) > sMap.get(leftChar)) {
                    have--;
                }
                left++;
            }
        }
        left = res[0];
        int right = res[1];
        if (resLen != Integer.MAX_VALUE) {
            return s.substring(left, right + 1);
        } else {
            return "";
        }
    }
}
