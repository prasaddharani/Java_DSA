package org.example.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Array {
    /*
    Input: nums = [2,2,1,1,1,2,2]
    Output: 2
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int majority = nums.length / 2;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > majority) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /*
    Input: nums = [1,2,3,4]
    Output: [24,12,8,6]
     */

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int prod = 1;
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(nums));
        for (int i = nums.length - 2; i >= 0; i--) {
            prod *= nums[i + 1];
            res[i] *= prod;
        }
        return  res;
    }

    /*
    Input: nums = [1,2,0]
    Output: 3
    Explanation: The numbers in the range [1,2] are all in the array.
     */

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            if (val >= 1 && val <= n) {
                if (nums[val - 1] == 0) {
                    nums[val - 1] = -1 * (n + 1);
                } else if (nums[val - 1] > 0) {
                    nums[val - 1] *= -1;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (nums[i - 1] >= 0) {
                return i;
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {
        Array array = new Array();
        int[] nums = {2,2,1,1,1,2,2};
        //System.out.println(array.majorityElement(nums));
        //System.out.println(Arrays.toString(array.productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(array.firstMissingPositive((new int[]{1, 3, 0})));
    }
}


public class Blind75 {
}
