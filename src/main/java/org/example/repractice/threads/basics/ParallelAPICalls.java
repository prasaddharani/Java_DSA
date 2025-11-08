package org.example.repractice.threads.basics;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ParallelAPICalls {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> api1 = () -> {
            log.info("Calling API-1 on thread {}", Thread.currentThread().getName());
            return "Response from API-1";
        };
        Callable<String> api2 = () -> {
            log.info("Calling API-2 on thread {}", Thread.currentThread().getName());
            return "Response from API-2";
        };
        Callable<String> api3 = () -> {
            log.info("Calling API-3 on thread {}", Thread.currentThread().getName());
            return "Response from API-3";
        };
        Callable<String> api4 = () -> {
            log.info("Calling API-4 on thread {}", Thread.currentThread().getName());
            return "Response from API-4";
        };
        Callable<String> api5 = () -> {
            log.info("Calling API-5 on thread {}", Thread.currentThread().getName());
            return "Response from API-5";
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = fixedThreadPool.invokeAll(List.of(api1, api2, api3, api4, api5));

        for (Future<String> future: futures) {
            log.info("Result: {}", future.get());
        }
        fixedThreadPool.shutdown();
    }
}
