package org.example.repractice.leetcode;


import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

class BitManipulations {

    public static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        int offset = 1;
        for (int i = 1; i < n + 1; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }

    public static int[] singleNumber(int[] nums) {
        int res = 0;
        for (Integer num: nums) {
            res = res ^ num;
        }
        int diff_bit = 1;
        while ((diff_bit & res) == 0) {
            diff_bit = diff_bit << 1;
        }

        int a = 0, b = 0;
        for (Integer num: nums) {
            if ((num & diff_bit) == 0) {
                a = a ^ num;
            } else {
                b = b ^ num;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(countBits(5)));
        System.out.println(Arrays.toString(singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }
}

class HashTables {

    private static String sortString(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return Arrays.toString(chars);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            String sorted = sortString(str);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }
        return map.values().stream().toList();
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int length;
        int maxLength = 0;
        for (Integer num: nums) {
            if (numSet.contains(num - 1)) {
                continue;
            }
            length = 1;
            while (numSet.contains(num + length)) {
                length += 1;
            }
            maxLength = max(maxLength, length);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        //System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}

class TwoPointers {

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;
        while (l < r) {
            maxArea = max(maxArea, (r - l) * (min(height[l], height[r])));
            if (height[r] > height[l]) {
                l += 1;
            } else {
                r -= 1;
            }
        }
        return maxArea;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int threeSum = nums[i] + nums[l] + nums[r];
                if (threeSum == 0) {
                    res.add(List.of(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                } else if (threeSum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    public static int trap(int[] height) {
        int maxLeft = Integer.MIN_VALUE, maxRight = Integer.MIN_VALUE;
        int l = 0, r = height.length - 1;
        int trap = 0;
        while (l < r) {
            if (maxLeft < maxRight) {
                l++;
                maxLeft = max(maxLeft, height[l]);
                trap += maxLeft - height[l];
            } else {
                r--;
                maxRight = max(maxRight, height[r]);
                trap += maxRight - height[r];
            }
        }
        return trap;
    }

    public static void main(String[] args) {
        //System.out.println(maxArea((new int[]{1,8,6,2,5,4,8,3,7})));
        //System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

class PrefixSum {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        int count = 0;
        int prefixSum = 0;
        for (Integer num: nums) {
            prefixSum += num;
            if (prefixCount.containsKey(prefixSum - k)) {
                count = 1 + prefixCount.get(prefixSum - num);
            }
            int finalPrefixSum = prefixSum;
            prefixCount.computeIfAbsent(
                    prefixSum, key -> 1 + prefixCount.getOrDefault(finalPrefixSum, 0));
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
    }
}

class FixedWindow {

    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            pCount.put(p.charAt(i), 1 + pCount.getOrDefault(p.charAt(i), 0));
            sCount.put(s.charAt(i), 1 + sCount.getOrDefault(s.charAt(i), 0));
        }
        if (pCount.equals(sCount)) {
            res.add(0);
        }
        System.out.println(sCount);
        int l = 0;
        for (int j = p.length(); j < s.length(); j++) {
            sCount.put(s.charAt(j), 1 + sCount.getOrDefault(s.charAt(j), 0));
            sCount.put(s.charAt(l), sCount.get(s.charAt(l)) - 1);
            if (sCount.get(s.charAt(l)) == 0) {
                sCount.remove(s.charAt(l));
            }
            l++;
            if (pCount.equals(sCount)) {
                res.add(l);
            }
        }
        return res;
    }

    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            pCount.put(s1.charAt(i), 1 + pCount.getOrDefault(s1.charAt(i), 0));
            sCount.put(s2.charAt(i), 1 + sCount.getOrDefault(s2.charAt(i), 0));
        }
        if (pCount.equals(sCount)) {
            return true;
        }
        System.out.println(sCount);
        int l = 0;
        for (int j = s1.length(); j < s2.length(); j++) {
            sCount.put(s2.charAt(j), 1 + sCount.getOrDefault(s2.charAt(j), 0));
            sCount.put(s2.charAt(l), sCount.get(s2.charAt(l)) - 1);
            if (sCount.get(s2.charAt(l)) == 0) {
                sCount.remove(s2.charAt(l));
            }
            l++;
            if (pCount.equals(sCount)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }
}

public class Blind75 {
}
