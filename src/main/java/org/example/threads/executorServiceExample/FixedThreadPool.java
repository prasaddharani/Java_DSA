package org.example.threads.executorServiceExample;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Runnable task1 = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Task 1 Count: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Task 1 interrupted: " + e.getMessage());
            }
        }
    };

    Runnable task2 = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Task 2 Count: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Task 2 interrupted: " + e.getMessage());
            }
        }
    };
    public void executeTasks() {
        List<Runnable> tasks = List.of(task1, task2);
        for (Runnable task : tasks) {
            executorService.submit(task);
        }
        executorService.shutdown(); // Shutdown the executor service after tasks are submitted
    }

    public static void main(String[] args) {
        FixedThreadPool executorDemo = new FixedThreadPool();
        executorDemo.executeTasks();
    }
}
