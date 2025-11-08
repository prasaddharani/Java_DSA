package org.example.repractice.threads.forkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Integer> {
    private final int start;
    private final int end;
    private final int[] arr;
    private static final int THRESHOLD = 3;

    SumTask(int start, int end, int[] arr) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " prints " + i);
                sum += arr[i];
            }
            return sum;
        }
        int mid = (start + end) / 2;
        SumTask left = new SumTask(start, mid, arr);
        SumTask right = new SumTask(mid, end, arr);

        left.fork();              // run left asynchronously
        int rightResult = right.compute(); // compute right synchronously
        int leftResult = left.join();      // wait and get left result

        return leftResult + rightResult;
    }
}

public class ForkJoinTaskExample {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int res = pool.invoke(new SumTask(0, arr.length, arr));
        System.out.println("Final sum: " + res);
        pool.shutdown();
    }
}
