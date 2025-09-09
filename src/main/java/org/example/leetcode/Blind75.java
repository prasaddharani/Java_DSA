package org.example.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Math.max;

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
        //System.out.println(array.firstMissingPositive((new int[]{1, 3, 0})));
    }
}

class StringProblems {
    /*
    Input: s = "abc", t = "ahbgdc"
    Output: true
    */
    public boolean isSubsequence(String s, String t) {
        int s_length = s.length();
        int t_length = t.length();
        if (s_length > t_length) {
            return  false;
        }
        int length = 0;
        for (char c: t.toCharArray()) {
            if (s.charAt(length) == c) {
                length += 1;
            }
            if (length == s_length) {
                return true;
            }
        }
        return  false;
    }

    /*
    Input: s = "the sky is blue"
    Output: "blue is sky the"
     */
    public String reverseWords(String s) {
       String[] strings = s.trim().split("\\s+");
       List<String> res = new ArrayList<>();
       for (int i = strings.length - 1; i >= 0; i--) {
           res.add(strings[i]);
//           if (i > 0) {
//               res.add(" ");
//           }
       }
       StringBuilder sb = new StringBuilder();
       res.forEach(sb::append);
       String stream = Arrays.stream(strings).collect(Collectors.collectingAndThen(Collectors.toList(),
               list -> {
                Collections.reverse(list);
                return String.join(" ", list);
               }));
       System.out.println(stream);
       return String.join(" ", res);
    }


    public static void main(String[] args) {
        StringProblems string = new StringProblems();
        //System.out.println(string.isSubsequence("abc", "ahbgdc"));
        //System.out.println(string.reverseWords("the sky is blue"));
    }
}

class HashTables {

    /*
    Input: strs = ["eat","tea","tan","ate","nat","bat"]

    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Function<String, String> sort = string -> {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        };

        Map<String, List<String>> res = new HashMap<>();

        for (String str: strs) {
            String sortedString = sort.apply(str);
            res.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(str);
        }

        return res.values().stream().toList();
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();

        for (String str : strs) {
            int[] freq = new int[26]; // frequency count for a-z
            for (char c : str.toCharArray()) {
                freq[c - 'a']++;
            }

            // Convert frequency array to string key
            String key = Arrays.toString(freq);
            System.out.println(key);

            res.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(res.values()); // mutable list
    }

    /*
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     */

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int maxLength = 0;
        for (int num: nums) {
            if (!set.contains(num - 1)) {
                int length = 0;
                while (set.contains(num + length)) {
                    length += 1;
                }
                maxLength = max(maxLength, length);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        HashTables hashTables = new HashTables();
        //System.out.println(hashTables.groupAnagrams1(new String[] {"eat","tea","tan","ate","nat","bat"}));
        System.out.println(hashTables.longestConsecutive(new int[]{100,4,200,1,3,2}));
    }
}


public class Blind75 {
}
