package org.example.threads.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class PrintArrayTask extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10; // Threshold for splitting tasks

    public PrintArrayTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            // If the task is small enough, print the elements directly
            for (int i = start; i < end; i++) {
                System.out.println(array[i]);
            }
        } else {
            // Otherwise, split the task into two smaller tasks
            int mid = (start + end) / 2;
            PrintArrayTask leftTask = new PrintArrayTask(array, start, mid);
            PrintArrayTask rightTask = new PrintArrayTask(array, mid, end);

            invokeAll(leftTask, rightTask);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        PrintArrayTask task = new PrintArrayTask(array, 0, array.length);

        // Execute the task using a ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        // Shutdown the pool
        pool.shutdown();
    }
}
