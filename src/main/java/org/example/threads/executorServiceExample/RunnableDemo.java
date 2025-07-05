package org.example.threads.executorServiceExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableDemo {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Runnable task1 = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Task 1 Count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Task 1 interrupted: " + e.getMessage());
            }
        }
    };

    Runnable task2 = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Task 2 Count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Task 2 interrupted: " + e.getMessage());
            }
        }
    };
    public void executeTasks() {
        List<Runnable> tasks = List.of(task1, task2);
        List<Future<?>> futures = new ArrayList<>();
        for (Runnable task : tasks) {
            futures.add(executorService.submit(task));
        }
        futures.forEach(future -> {
            try {
                future.get(); // Wait for each task to complete
            } catch (Exception e) {
                System.out.println("Error while executing task: " + e.getMessage());
            }
        });
        executorService.shutdown(); // Shutdown the executor service after tasks are submitted
    }

    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        runnableDemo.executeTasks();
    }
}
