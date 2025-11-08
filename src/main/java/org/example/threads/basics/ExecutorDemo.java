package org.example.threads.basics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.OffsetDateTime;
import java.util.concurrent.*;
import java.util.stream.IntStream;

// chatgpt: https://chatgpt.com/c/690ad50f-7034-8324-b851-600a012fc629
@Slf4j
public class ExecutorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadPoolExecutor = Executors.newFixedThreadPool(5);
        ThreadPoolTaskExecutor threadFactory = new ThreadPoolTaskExecutor();
        threadFactory.setCorePoolSize(2);
        ExecutorService cachedThreadPoolExecutor = Executors.newCachedThreadPool(threadFactory);
        ScheduledExecutorService scheduledThreadPoolExecutor =
                Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        log.info("Availablle processors: {}", Runtime.getRuntime().availableProcessors());

//        IntStream.range(0, 10).forEach(i ->
//                singleThreadExecutor.submit(() -> log.info("Single Thread executor is running with name: {} " +
//                        "and index: {}", Thread.currentThread().getName(), i)));
//
//        IntStream.range(0, 10).forEach(i ->
//                fixedThreadPoolExecutor.submit(() -> log.info("Fixed Thread pool executor is running with name: {} " +
//                        "and index: {}", Thread.currentThread().getName(), i)));
//
//        IntStream.range(0, 10).forEach(i ->
//                cachedThreadPoolExecutor.submit(() -> log.info("Cache Thread pool executor is running with name: {} " +
//                        "and index: {}", Thread.currentThread().getName(), i)));
//        cachedThreadPoolExecutor.shutdown();

//        IntStream.range(0, 10).forEach(i ->
//        {
//           scheduledThreadPoolExecutor.submit(() -> log.info("Scheduled Thread pool executor is running with name: {} " +
//                   "and index: {}", Thread.currentThread().getName(), i));
//        });

        Runnable task = () -> log.info("Runnable Task is running at: {}", OffsetDateTime.now());
        Callable<OffsetDateTime> callable = () -> {
            OffsetDateTime now = OffsetDateTime.now();
            log.info("Callable Task is running at: {}", now);
            return now;
        };

        Future<OffsetDateTime> result =
                scheduledThreadPoolExecutor.schedule(callable, 2, TimeUnit.SECONDS);
        log.info("Result for callable task: {}", result.get());

        // run task repeatedly
        //scheduledThreadPoolExecutor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.shutdown();
    }
}
