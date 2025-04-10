package main.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixSumProblems {

    public static void main(String[] args) {

    }

    /*
    303. Range Sum Query - Immutable

    Input
    ["NumArray", "sumRange", "sumRange", "sumRange"]
    [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
    Output
    [null, 1, -1, -3]
     */

    class NumArray {
        List<Integer> prefixSumList = new ArrayList<>();
        int prefixSum = 0;
        public NumArray(int[] nums) {
            for (int num : nums) {
                prefixSum += num;
                prefixSumList.add(prefixSum);
            }
        }

        public int sumRange(int left, int right) {
            int leftPrefix = left > 0 ? prefixSumList.get(left - 1) : 0;
            int rightPrefix = prefixSumList.get(right);
            return rightPrefix - leftPrefix;
        }
    }

    /*
    560. Subarray Sum Equals K

    Input: nums = [1,1,1], k = 2
    Output: 2
     */

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        int prefixSum = 0;
        int res = 0;
        for (int num : nums) {
            prefixSum += num;
            if (prefixSumMap.containsKey(prefixSum - k)) {
                res += prefixSumMap.get(prefixSum - k);
            }
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }

    /*
    523. Continuous Subarray Sum

    Input: nums = [23,2,4,6,7], k = 6
    Output: true
    Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
     */

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int rem = prefixSum % k;
            if (!remainderMap.containsKey(rem)) {
                remainderMap.put(rem, i);
            } else if (i - remainderMap.get(rem) > 1) {
                return true;
            }
        }
        return false;
    }

    /*
    525. Contiguous Array

    Input: nums = [0,1]
    Output: 2
    Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
     */

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);
        int res = 0;
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i] == 0 ? -1: 1;
            prefixSum += num;
            if (prefixSumMap.containsKey(prefixSum)) {
                res = Math.max(res, i - prefixSumMap.get((prefixSum)));
            } else {
                prefixSumMap.put(prefixSum, i);
            }
        }
        return res;
    }
}
