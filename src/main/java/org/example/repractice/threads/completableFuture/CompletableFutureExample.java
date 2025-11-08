package org.example.repractice.threads.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

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
    }
}
