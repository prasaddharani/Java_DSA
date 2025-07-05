package org.example.threads.executorServiceExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
        // Task to run after a 2-second delay
        scheduledExecutor.schedule(() -> {
            System.out.println("Task 1 executed after 2 seconds delay on " + Thread.currentThread().getName());
        }, 2, TimeUnit.SECONDS);

        // Task to run repeatedly every 3 seconds, after an initial 1-second delay
        scheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.println("Repeating task executed at " + System.currentTimeMillis() + " on " + Thread.currentThread().getName());
        }, 1, 3, TimeUnit.SECONDS);

        // Let the main thread sleep for 10 seconds to observe scheduled tasks
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scheduledExecutor.shutdown();
    }
}
