package org.example.leetcode;

public class BinarySearchProblems {

    public static void main(String[] args) {

    }

    /*
    34. Find First and Last Position of Element in Sorted Array
     */

    /*
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]
     */

    public int[] searchRange(int[] nums, int target) {
        int left = binarySearchHelper(nums, target, true);
        int right = binarySearchHelper(nums, target, false);
        return new int[]{left, right};
    }

    private int binarySearchHelper(int[] nums, int target, boolean leftBias) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                res = mid;
                if (leftBias) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return res;
    }

    /*
    33. Search in Rotated Sorted Array
     */


    /*
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4
     */

    public int search(int[] nums, int target) {
        return modifiedBinarySearch(nums, target, 0, nums.length - 1);
    }

    public int modifiedBinarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + ((right - left) / 2);

        if (nums[mid] == target) {
            return mid;
        }

        // left half is sorted
        if (nums[mid] >= nums[left]) {
            if (target <= nums[mid] && target >= nums[left]) {
                return modifiedBinarySearch(nums, target, left, mid - 1);
            } else {
                return modifiedBinarySearch(nums, target, mid + 1, right);
            }
        } else {
            // right half is sorted
            if (target >= nums[mid] && target <= nums[right]) {
                return modifiedBinarySearch(nums, target, mid + 1, right);
            } else {
                return modifiedBinarySearch(nums, target, left, mid - 1);
            }
        }
    }

    /*
    162. Find Peak Element
     */

    /*
    Input: nums = [1,2,3,1]
    Output: 2
    Explanation: 3 is a peak element and your function should return the index number 2.
     */

    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /*
    875. Koko Eating Bananas
     */

    /*
    Input: piles = [3,6,7,11], h = 8
    Output: 4
     */

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = piles[0];
        for (Integer pile: piles) {
            if (pile > right) {
                right = pile;
            }
        }
        int res = right;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            long hours = 0;

            for (int pile : piles) {
                hours += (pile + mid - 1L) / mid; //
            }
            if (hours <= h) {
                res = Math.min(res, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    /*
    74. Search a 2D Matrix
     */

    /*
    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    Output: true
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = searchPotentialRow(matrix, target);
        if (rowIndex == -1) {
            return false;
        } else {
            return binarySearchOnRow(matrix, rowIndex, target);
        }
    }

    private int searchPotentialRow(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] <= target && matrix[mid][matrix[mid].length - 1] >= target) {
                return mid;
            } else if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private boolean binarySearchOnRow(int[][] matrix, int rowIndex, int target) {
        int left = 0, right = matrix[rowIndex].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[rowIndex][mid] == target) {
                return true;
            } else if (matrix[rowIndex][mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    /*
    4. Median of Two Sorted Arrays
     */

    /*
    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int x = nums1.length;
        int y = nums2.length;

        int left = 0, right = x;

        while (left <= right) {
            int partitionX = left + (right - left) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or valid");
    }

}
