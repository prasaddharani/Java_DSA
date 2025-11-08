package org.example.repractice.threads.forkJoinPool;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

class PrintTask extends RecursiveAction {
    private int start, end;
    private static final int THRESHOLD = 3;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) <= THRESHOLD) {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + " prints " + i);
            }
        } else {
            int mid = (start + end) / 2;
            PrintTask left = new PrintTask(start, mid);
            PrintTask right = new PrintTask(mid + 1, end);

            left.fork();   // Split and run asynchronously
            right.compute(); // Run one directly (to utilize current thread)
            left.join();   // Wait for left to finish
        }
    }
}

public class ForkJoinActionExample {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new PrintTask(1, 10));
        pool.shutdown();
    }
}