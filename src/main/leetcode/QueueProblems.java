package main.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
}
