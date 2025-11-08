package org.example.repractice.threads.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
class RunAsyncExample {
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> log.info("RunAsync task is running"));
        future.join();
    }
}

@Slf4j
class SupplyAsyncExample {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "SupplyAsync task is running");
        log.info(future.join());
    }
}

@Slf4j
public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Completable future")
                .thenApply(res -> res + " is built on top of Executor service");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " with functional style programming");

        CompletableFuture<String> res = future1.thenCombine(future2, (a, b) -> a + b);
        log.info("Result: {}", res.join());

        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Error occured");
            }
            return "Hello";
        }).exceptionally(throwable -> "recovered from: " + throwable.getMessage());

        log.info("Exceptionally Result: {}", exceptionally.join());

        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Error occurred");
        }).handle((result, ex) -> {
            if (ex != null) return "Handled error: " + ex.getMessage();
            return result;
        });

        System.out.println(future.join());


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> ap1 = CompletableFuture.supplyAsync(() ->
                "Running from Thread " + Thread.currentThread().getName()+" Call API-1", fixedThreadPool);
        CompletableFuture<String> ap2 = CompletableFuture.supplyAsync(() ->
                "Running from Thread " + Thread.currentThread().getName()+" Call API-1", fixedThreadPool);
        CompletableFuture<String> api3 = CompletableFuture.supplyAsync(() ->
                "Running from Thread " + Thread.currentThread().getName()+" Call API-1", fixedThreadPool);

        CompletableFuture<List<String>> allOf = collectResult(List.of(ap1, ap2, api3));
        List<String> results = allOf.join();
        fixedThreadPool.shutdown();

        log.info("Result: {}", results);

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> sleepAndPrint("Task 1"));
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> sleepAndPrint("Task 2"));
        CompletableFuture<Void> f3 = CompletableFuture.runAsync(() -> sleepAndPrint("Task 3"));


        CompletableFuture.allOf(f1, f2, f3).join(); // waits untill all tasks finishes off

        CompletableFuture<Object> any = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> "Result 1"),
                CompletableFuture.supplyAsync(() -> "Result 2")
        );
        System.out.println(any.join()); // whichever finishes first

        Supplier<String> supplier = () -> "hi";
        log.info("Supplier: {}", supplier.get());

        Function<String, String> function = (a)  -> a + " Hello";
        log.info("Function: {}", function.apply("Dharani"));
    }

    public static <T> CompletableFuture<List<T>> collectResult(List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join).toList());
    }

    static void sleepAndPrint(String name) {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        log.info("{} done by {}", name, Thread.currentThread().getName());
    }
}
