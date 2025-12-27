package org.example.repractice.leetcode;

import java.util.List;

public class StringProblems {

    public static boolean isSubsequence(String s, String t) {
        int l = 0;
        for (char c: t.toCharArray()) {
            if (c == s.charAt(l)) {
                l += 1;
            }
            if (l == s.length()) {
                return true;
            }
        }
        return false;
    }

    public static String reverseWords(String s) {
        List<String> strs = List.of(s.split("\\s+"));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strs.size() - 1; i >= 0; i--) {
            stringBuilder.append(strs.get(i));
            if (i != 0) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        //System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(reverseWords("the sky is blue"));
    }
}
