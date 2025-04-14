package main.leetcode;

import java.util.*;

public class StackProblems {
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }

    /*
    20. Valid Parentheses

    Input: s = "()[]{}"

    Output: true
     */

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character poppedItem = stack.pop();
                if (!((c == ')'  && poppedItem == '(') ||
                        (c == ']'  && poppedItem == '[') ||
                        (c == '}'  && poppedItem == '{'))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /*
    1047. Remove All Adjacent Duplicates In String

    Input: s = "abbaca"
    Output: "ca"
    Explanation:
    For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal,
    and this is the only possible move.  The result of this move is that the string is "aaca",
    of which only "aa" is possible, so the final string is "ca".
    */

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c: s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /*
    155. Min Stack

    Input
    ["MinStack","push","push","push","getMin","pop","top","getMin"]
    [[],[-2],[0],[-3],[],[],[],[]]

    Output
    [null,null,null,null,-3,null,0,-2]

    Explanation
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin(); // return -3
    minStack.pop();
    minStack.top();    // return 0
    minStack.getMin(); // return -2
     */

    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.push(val);
            }
        }

        public void pop() {
            int val = stack.pop();
            if (minStack.peek() == val) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    /*
    316. Remove Duplicate Letters

    Input: s = "bcabc"
    Output: "abc"
     */

    public String removeDuplicateLetters(String s) {
        Set<Character> setDS = new HashSet<>();
        Map<Character, Integer> hashMap = new HashMap<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            hashMap.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (setDS.contains(c)) {
                continue;
            } else {
                while (!stack.isEmpty() && stack.peek() > c && hashMap.get(stack.peek()) > i) {
                    setDS.remove(stack.peek());
                    stack.pop();
                }
                stack.push(c);
                setDS.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /*
    2390. Removing Stars From a String

    Input: s = "leet**cod*e"
    Output: "lecoe"
    Explanation: Performing the removals from left to right:
    - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
    - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
    - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
    There are no more stars, so we return "lecoe".
     */

    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c: s.toCharArray()) {
            if (c == '*' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /*
    150. Evaluate Reverse Polish Notation

    Input: tokens = ["2","1","+","3","*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
     */

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token: tokens) {
            if (Objects.equals(token, "+") || Objects.equals(token, "-") || Objects.equals(token, "*") || Objects.equals(token, "/")) {
                int m = Integer.parseInt(stack.pop());
                int n = Integer.parseInt(stack.pop());
                switch (token) {
                    case "+":
                        stack.push(String.valueOf(n + m));
                        break;
                    case "-":
                        stack.push(String.valueOf(n - m));
                        break;
                    case "*":
                        stack.push(String.valueOf(n * m));
                        break;
                    case "/":
                        stack.push(String.valueOf(n / m));
                        break;
                }
            }
            else {
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.peek());
    }

    /*
    227. Basic Calculator II

    Input: s = "3+2*2"
    Output: 7
     */

    public int calculate(String s) {
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = (num * 10) + (c - '0'); // Correct conversion
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                sign = c;
            }
        }
        int res = 0;
        for (Integer n: stack) {
            res += n;
        }
        return res;
    }

    /*
    32. Longest Valid Parentheses

    Input: s = ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()".
     */

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxCount = Math.max(maxCount, i - stack.peek());
                }
            }
        }
        return maxCount;
    }




    /*
    MONOTONIC STACK
     */


    /*
    496. Next Greater Element I

    Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    Output: [-1,3,-1]
    Explanation: The next greater element for each value of nums1 is as follows:
    - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
    - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
    - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     */

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums1.length];
        for (Integer num: nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                hashMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = hashMap.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    /*
    739. Daily Temperatures

    Input: temperatures = [73,74,75,71,69,72,76,73]
    Output: [1,1,4,2,1,1,0,0]
     */

    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prevIndex = stack.pop();
                res[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return res;
    }

    /*
    901. Online Stock Span

    Input
    ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
    [[], [100], [80], [60], [70], [60], [75], [85]]
    Output
    [null, 1, 1, 1, 2, 1, 4, 6]

    Explanation
    StockSpanner stockSpanner = new StockSpanner();
    stockSpanner.next(100); // return 1
    stockSpanner.next(80);  // return 1
    stockSpanner.next(60);  // return 1
    stockSpanner.next(70);  // return 2
    stockSpanner.next(60);  // return 1
    stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
    stockSpanner.next(85);  // return 6
     */

    class StockSpanner {
        Stack<StockPrice> stack;

        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek().price <= price) {
                span += stack.peek().span;
                stack.pop();
            }
            stack.push(new StockPrice(span, price));
            return span;
        }

        class StockPrice {
            int span;
            int price;

            public StockPrice(int span, int price) {
                this.span = span;
                this.price = price;
            }
        }
    }

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */

    /*
    456. 132 Pattern

    Input: nums = [3,1,4,2]
    Output: true
    Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
     */

    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int j = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (num < j) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < num) {
                j = stack.pop();
            }
            stack.push(num);
        }
        return false;
    }

    /*
    1944. Number of Visible People in a Queue

    Input: heights = [10,6,8,5,11,9]
    Output: [3,1,2,1,1,0]
    Explanation:
    Person 0 can see person 1, 2, and 4.
    Person 1 can see person 2.
    Person 2 can see person 3 and 4.
    Person 3 can see person 4.
    Person 4 can see person 5.
    Person 5 can see no one since nobody is to the right of them.
     */

    public int[] canSeePersonsCount(int[] heights) {
        int[] res = new int[heights.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            int count = 0;
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
                count++;
            }
            if (!stack.isEmpty()) {
                count++;
            }
            stack.push(heights[i]);
            res[i] = count;
        }
        return res;
    }

    /*
    84. Largest Rectangle in Histogram

    Input: heights = [2,1,5,6,2,3]
    Output: 10
    Explanation: The above is a histogram where width of each bar is 1.
    The largest rectangle is shown in the red area, which has an area = 10 units.
     */

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Histogram> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            int start = i;
            while (!stack.isEmpty() && stack.peek().height > heights[i]) {
                int index = stack.peek().index;
                int height = stack.peek().height;
                stack.pop();
                maxArea = Math.max(maxArea, height * (i - index));
                start = index;
            }
            stack.push(new Histogram(start, heights[i]));
        }

        for (Histogram histogram : stack) {
            maxArea = Math.max(maxArea, histogram.height * (heights.length - histogram.index));
        }
        return maxArea;
    }

    class Histogram {
        int index;
        int height;
        public Histogram(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
