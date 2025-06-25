package org.example.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Backtracking {
    public static void main(String[] args) {
        //System.out.println(generateParenthesis(3));
        //System.out.println(permute(new int[]{1, 2, 3}));
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    /*
    22. Generate Parentheses
     */

    /*
    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]
     */

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTrack(0, 0, "", n, res);
        return res;
    }

    private static void backTrack(int prefixCount, int suffixCount, String path, int n, List<String> res) {
        if (prefixCount == n && suffixCount == n) {
            res.add(path);
            return;
        }

        if (prefixCount <= n) {
            backTrack(prefixCount + 1, suffixCount, path + "(", n, res);
        }

        if (suffixCount < prefixCount) {
            backTrack(prefixCount, suffixCount + 1, path + ")", n, res);
        }
    }

    /*
    46. Permutations
     */

    /*
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(used, new Stack<>(), nums, res);
        return res;
    }

    private static void backtrack(boolean[] used, Stack<Integer> path, int[] nums, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.push(nums[i]);
            backtrack(used, path, nums, res);
            path.pop();
            used[i] = false;
        }
    }

    /*
    78. Subsets
     */

    /*
    Input: nums = [1,2,3]
    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, res, new Stack<>(), nums);
        return res;
    }

    private static void backtrack(int start, List<List<Integer>> res, Stack<Integer> path, int[] nums) {
        res.add(new ArrayList<>(path));
        for (int end = start; end < nums.length; end++) {
            path.add(nums[end]);
            backtrack(end + 1, res, path, nums);
            path.pop();
        }
    }

    /*
    131. Palindrome Partitioning
     */

    /*
    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]
     */

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                path.add(s.substring(start, end + 1));
                backtrack(s, end + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    /*
    51. N-Queens
     */

    /*
    Input: n = 4
    Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
     */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> posDiagonals = new HashSet<>();
        Set<Integer> negDiagonals = new HashSet<>();
        char[][] board = new char[n][n];

        // Initialize the board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(0, n, board, cols, posDiagonals, negDiagonals, res);
        return res;
    }

    private void backtrack(int row, int n, char[][] board, Set<Integer> cols,
                           Set<Integer> posDiagonals, Set<Integer> negDiagonals, List<List<String>> res) {
        if (row == n) {
            List<String> copy = new ArrayList<>();
            for (char[] boardRow : board) {
                copy.add(new String(boardRow));
            }
            res.add(copy);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || posDiagonals.contains(row + col) || negDiagonals.contains(row - col)) {
                continue;
            }

            // Place queen
            cols.add(col);
            posDiagonals.add(row + col);
            negDiagonals.add(row - col);
            board[row][col] = 'Q';

            backtrack(row + 1, n, board, cols, posDiagonals, negDiagonals, res);

            // Remove queen (backtrack)
            cols.remove(col);
            posDiagonals.remove(row + col);
            negDiagonals.remove(row - col);
            board[row][col] = '.';
        }
    }
}
