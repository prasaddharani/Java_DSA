package main.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointerProblems {
    public static void main(String[] args) {

    }

    /*
    88. Merge Sorted Array

    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]
    Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
    The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r = m + n - 1;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[r] = nums1[m - 1];
                m--;
            } else {
                nums1[r] = nums2[n - 1];
                n--;
            }
            r--;
        }

        while (n > 0) {
            nums1[r] = nums2[n - 1];
            n--;
            r--;
        }
    }

    /*
    167. Two Sum II - Input Array Is Sorted

    Input: numbers = [2,7,11,15], target = 9
    Output: [1,2]
    Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
     */

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int twoSum = numbers[left] + numbers[right];
            if (target == twoSum) {
                return new int[]{left + 1, right + 1};
            } else if (target < twoSum) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    /*
    11. Container With Most Water

     Input: height = [1,8,6,2,5,4,8,3,7]
    Output: 49
    Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
    */

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[right], height[left]) * (right - left));
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    /*
    15. 3Sum

    Input: nums = [-1,0,1,2,-1,-4]
    Output: [[-1,-1,2],[-1,0,1]]
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int start = 0; start < nums.length - 2; start++) {

            if (start > 0 && nums[start] == nums[start - 1]) {
                continue;
            }
            int left = start + 1;
            int right = nums.length - 1;

            while (left < right) {
                int threeSum = nums[start] + nums[left] + nums[right];
                if (threeSum == 0) {
                    res.add(List.of(nums[start], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (threeSum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    /*
    42. Trapping Rain Water

    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

     */

    public int trap(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int maxL = height[left];
        int maxR = height[right];
        while (left < right) {
            if (maxL < maxR) {
                left++;
                maxL = Math.max(maxL, height[left]);
                int trap = maxL - height[left];
                res += trap;
            } else {
                right++;
                maxR = Math.max(maxR, height[right]);
                int trap = maxR - height[right];
                res += trap;
            }
        }
        return res;
    }
}
