package org.example.threads.executorServiceExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    // Callable task that returns a result
    Callable<String> callableTask1 = () -> {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " is executing the callable task with count - " + i);
        }
        // Simulate some work
        Thread.sleep(1000);
        return "Result from " + threadName;
    };

    Callable<String> callableTask2 = () -> {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " is executing the callable task with count - " + i);
        }
        // Simulate some work
        Thread.sleep(1000);
        return "Result from " + threadName;
    };

    public void executeCallables() {
        try {
            List<Callable<String>> callables = List.of(callableTask1, callableTask2);
            List<Future<String>> futures = new ArrayList<>(executorService.invokeAll(callables));
            // Submit all callables and collect futures
            for (Future<String> future : futures) {
                // Get the result of each callable task
                String result = future.get();
                System.out.println("Callable task completed with result: " + result);
            }
            executorService.shutdown(); // Shutdown the executor service after tasks are submitted

        } catch (Exception e) {
            System.out.println("Error executing callable tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        callableDemo.executeCallables();
    }
}
