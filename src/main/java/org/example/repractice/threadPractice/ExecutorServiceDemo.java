package org.example.repractice.threadPractice;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixedThreadPool.submit(() ->
                    System.out.println("Task " + taskId + " executed by " +
                            Thread.currentThread().getName()));
        }

        fixedThreadPool.shutdown();

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> future = singleThreadExecutor.submit(task);
        try {
            Integer value = future.get();
            System.out.println("Callable task: " + value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        singleThreadExecutor.shutdown();


        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        scheduledThreadPool.scheduleAtFixedRate(() ->
                System.out.println("Task is running every 2 sec"), 1, 2, TimeUnit.SECONDS);

        scheduledThreadPool.shutdown();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedThreadPool
                    .submit(() -> System.out.println("Running cached thread pool" + Thread.currentThread().getName()));
        }
        cachedThreadPool.shutdown();
    }
}
