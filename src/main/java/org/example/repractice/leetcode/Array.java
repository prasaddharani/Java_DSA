package org.example.repractice.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Array {

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int majority = nums.length / 2;

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > majority) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /*

    [1,2,3,4]
    [1, 1, 2, 6]
    [    , 4, 1]

    [24,12,8,6]
     */

    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int prod = 1;

        for (int j = nums.length - 1; j >= 0; j--) {
            res[j] = prod * res[j];
            prod *= nums[j];
        }
        return res;
    }

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < nums.length; i++) {
           if (nums[i] < 0) {
               nums[i] = 0;
           }
        }

        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            if (1 <= val && val <= n) {
                if (nums[val - 1] > 0) {
                    nums[val - 1] *= -1;
                } else if (nums[val - 1] == 0) {
                    nums[val - 1] *= -(n + 1);
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
        //System.out.println(majorityElement(new int[]{3, 2, 3}));
        //System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
    }
}
