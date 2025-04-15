package main.leetcode;

import java.util.*;
import java.util.LinkedList;

public class QueueProblems {
    public static void main(String[] args) {

    }

    /*
    933. Number of Recent Calls

    Input
    ["RecentCounter", "ping", "ping", "ping", "ping"]
    [[], [1], [100], [3001], [3002]]
    Output
    [null, 1, 2, 3, 3]

    Explanation
    RecentCounter recentCounter = new RecentCounter();
    recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
    recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
    recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
    recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
     */

    class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            // Add the current ping time to the queue
            queue.offer(t);

            // Remove ping times that are older than (t - 3000)
            while (!queue.isEmpty() && t - queue.peek() > 3000) {
                queue.poll();
            }

            // Return the number of pings within the last 3000 milliseconds
            return queue.size();
        }
    }

    /*
    2073. Time Needed to Buy Tickets

    Input: tickets = [2,3,2], k = 2

    Output: 6

    Explanation:

    The queue starts as [2,3,2], where the kth person is underlined.
    After the person at the front has bought a ticket, the queue becomes [3,2,1] at 1 second.
    Continuing this process, the queue becomes [2,1,2] at 2 seconds.
    Continuing this process, the queue becomes [1,2,1] at 3 seconds.
    Continuing this process, the queue becomes [2,1] at 4 seconds. Note: the person at the front left the queue.
    Continuing this process, the queue becomes [1,1] at 5 seconds.
    Continuing this process, the queue becomes [1] at 6 seconds. The kth person has bought all their tickets, so return 6.
         */

    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<BuyTicket> buyTickets = new LinkedList<>();
        for (int i = 0; i < tickets.length; i++) {
            buyTickets.add(new BuyTicket(i, tickets[i]));
        }
        int timeTaken = 0;
        while (!buyTickets.isEmpty()) {
            BuyTicket buyTicket = buyTickets.poll();
            int index = buyTicket.index;
            int ticket = buyTicket.ticket;
            ticket--;
            timeTaken++;
            if (ticket == 0 && index == (k - 1)) {
                return timeTaken;
            }
            if (ticket != 0) {
                buyTickets.add(new BuyTicket(index, ticket));
            }
        }
        return -1;
    }

    class BuyTicket {
        int index;
        int ticket;
        public BuyTicket(int index, int ticket) {
            this.index = index;
            this.ticket = ticket;
        }
    }

    public int timeRequiredToBuyWithoutQueue(int[] tickets, int k) {
        int timeTaken = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                timeTaken += Math.min(tickets[i], tickets[k]);
            } else {
                timeTaken += Math.min(tickets[i], tickets[k - 1]);
            }
        }
        return timeTaken;
    }

    /*
    950. Reveal Cards In Increasing Order

    Input: deck = [17,13,11,2,3,5,7]
    Output: [2,13,3,11,5,17,7]
    Explanation:
    We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
    After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
    We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
    We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
    We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
    We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
    We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
    We reveal 13, and move 17 to the bottom.  The deck is now [17].
    We reveal 17.
    Since all the cards revealed are in increasing order, the answer is correct.
     */

    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int[] res = new int[deck.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < deck.length; i++) {
            queue.offer(i);
        }

        for (Integer n: deck) {
            int index = queue.poll();
            res[index] = n;
            if (!queue.isEmpty()) {
                queue.offer(queue.poll());
            }
        }
        return res;
    }

    /*
    Monotonic Queue Problems
     */

    /*
    239. Sliding Window Maximum

    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [3,3,5,5,6,7]
    Explanation:
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int ri = 0; // result index

        for (int i = 0; i < n; i++) {
            // Remove indices out of window
            if (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }

            // Remove smaller values from the back
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.offerLast(i);

            // Start filling result when window hits size k
            if (i >= k - 1) {
                res[ri++] = nums[queue.peekFirst()];
            }
        }

        return res;
    }

    /*
    1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

    Input: nums = [8,2,4,7], limit = 4
    Output: 2
    Explanation: All subarrays are:
    [8] with maximum absolute diff |8-8| = 0 <= 4.
    [8,2] with maximum absolute diff |8-2| = 6 > 4.
    [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
    [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
    [2] with maximum absolute diff |2-2| = 0 <= 4.
    [2,4] with maximum absolute diff |2-4| = 2 <= 4.
    [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
    [4] with maximum absolute diff |4-4| = 0 <= 4.
    [4,7] with maximum absolute diff |4-7| = 3 <= 4.
    [7] with maximum absolute diff |7-7| = 0 <= 4.
    Therefore, the size of the longest subarray is 2.
     */

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();
        int maxRes = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] < nums[right]) {
                maxQueue.pollLast();
            }

            while (!minQueue.isEmpty() && nums[minQueue.peekLast()] > nums[right]) {
                minQueue.pollLast();
            }
            maxQueue.offer(right);
            minQueue.offer(right);

            while (nums[maxQueue.peekFirst()] - nums[minQueue.peekFirst()] > limit) {
                left++;
                if (maxQueue.peekFirst() < left) {
                    maxQueue.pollFirst();
                }

                if (minQueue.peekFirst() < left) {
                    minQueue.pollFirst();
                }
            }
            maxRes = Math.max(maxRes, right - left + 1);
        }
        return maxRes;
    }

    /*
    1696. Jump Game VI

    Input: nums = [1,-1,-2,4,-7,3], k = 2
    Output: 7
    Explanation: You can choose your jumps forming the subsequence [1,-1,4,3]
    (underlined above). The sum is 7.
     */

    public int maxResult(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        deque.offer(0);
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[deque.peekFirst()];

            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        return nums[nums.length - 1];
    }

    /*
    1499. Max Value of Equation

    Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
    Output: 4
    Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
    No other pairs satisfy the condition, so we return the max of 4 and 1.

    You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.

    Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.

    It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
     */

    public int findMaxValueOfEquation(int[][] points, int k) {
        // Deque to store pairs of (x, y - x)
        Deque<int[]> deque = new LinkedList<>();
        int maxValue = Integer.MIN_VALUE;

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            // Remove points from the front if they are out of the window (x - xi > k)
            while (!deque.isEmpty() && x - deque.peekFirst()[0] > k) {
                deque.pollFirst();
            }

            // If deque is not empty, compute the potential maximum value
            if (!deque.isEmpty()) {
                int[] front = deque.peekFirst();
                maxValue = Math.max(maxValue, y + x + front[1]);
            }

            // Maintain deque in decreasing order of (y - x)
            while (!deque.isEmpty() && y - x >= deque.peekLast()[1]) {
                deque.pollLast();
            }

            // Add current point to the deque
            deque.offerLast(new int[]{x, y - x});
        }

        return maxValue;
    }

}
