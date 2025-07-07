package org.example.threads.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Integer> {
    private final int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FibonacciTask task1 = new FibonacciTask(n - 1);
        FibonacciTask task2 = new FibonacciTask(n - 2);
        task1.fork(); // Start the first task asynchronously
        return task2.compute() + task1.join(); // Compute the second task and join the first
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int n = 10; // Example input
        FibonacciTask task = new FibonacciTask(n);
        int result = pool.invoke(task);
        System.out.println("Fibonacci of " + n + " is: " + result);
    }

}
