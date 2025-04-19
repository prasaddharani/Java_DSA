package main.leetcode;

public class BitManipulations {

    public static void main(String[] args) {

    }

    /*
    136. Single Number

    Input: nums = [4,1,2,1,2]

    Output: 4
     */

    public int singleNumber(int[] nums) {
        int res = 0;
        for (Integer num : nums) {
            res = res ^ num;
        }
        return res;
    }

    /*
    191. Number of 1 Bits

    Input: n = 128

    Output: 1
     */

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /*
    338. Counting Bits

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
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    /*
    190. Reverse Bits

    Input: n = 00000010100101000001111010011100
    Output:    964176192 (00111001011110000010100101000000)
    Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
     */

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = (n >> i) & 1;
            res = res | (bit << (31 - i));
        }
        return res;
    }

    /*
    201. Bitwise AND of Numbers Range

    Input: left = 5, right = 7
    Output: 4
     */

    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left != right) {
            left = left >> 1;
            right = right >> 1;
            shift++;
        }
        return left << shift;
    }

    /*
    371. Sum of Two Integers
    Input: a = 1, b = 2
    Output: 3
     */

    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
