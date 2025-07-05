package org.example.threads.executorServiceExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTypes {
    public void demonstrateExecutorTypes() {
        // FixedThreadPool: Fixed number of threads
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        System.out.println("Created FixedThreadPool");

        // SingleThreadExecutor: Only one thread
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println("Created SingleThreadExecutor");

        // CachedThreadPool: Creates new threads as needed, reuses idle threads
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("Created CachedThreadPool");

        // ScheduledThreadPool: Can schedule commands to run after a delay or periodically
        java.util.concurrent.ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("Created ScheduledThreadPool");

        // Always shutdown executors when done
        fixedThreadPool.shutdown();
        singleThreadExecutor.shutdown();
        cachedThreadPool.shutdown();
        scheduledThreadPool.shutdown();
    }

    public static void main(String[] args) {
        ExecutorTypes executorTypes = new ExecutorTypes();
        executorTypes.demonstrateExecutorTypes();
        System.out.println("Executor types demonstration completed.");
    }
}
