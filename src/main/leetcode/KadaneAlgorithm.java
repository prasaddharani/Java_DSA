package main.leetcode;

public class KadaneAlgorithm {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
    }

    /*
    53. Maximum Subarray

    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
     */

    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (Integer num: nums) {
            currentSum = Math.max(num + currentSum, num);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    /*
    152. Maximum Product Subarray

    Input: nums = [2,3,-2,4]
    Output: 6
    Explanation: [2,3] has the largest product 6.
     */

    public static int maxProduct(int[] nums) {
        int prefixProd = 0;
        int suffixProd = 0;
        int maxProd = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            prefixProd = prefixProd == 0 ? 1 : prefixProd;
            suffixProd = suffixProd == 0 ? 1 : suffixProd;
            prefixProd = prefixProd * nums[i];
            suffixProd = suffixProd * nums[nums.length - 1 - i];
            maxProd = Math.max(maxProd, Math.max(prefixProd, suffixProd));
        }
        return maxProd;
    }
}
