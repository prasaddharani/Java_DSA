package org.example.threads.executorServiceExample;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPoolExecutorService {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            cachedThreadPool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                LocalTime startTime = LocalTime.now();
                System.out.println("Task " + taskId + " started on " + threadName + " at " + startTime);
                try {
                    Thread.sleep(500 + random.nextInt(1000)); // Simulate variable work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                LocalTime endTime = LocalTime.now();
                System.out.println("Task " + taskId + " completed on " + threadName + " at " + endTime);
            });
        }
        cachedThreadPool.shutdown();
    }
}
