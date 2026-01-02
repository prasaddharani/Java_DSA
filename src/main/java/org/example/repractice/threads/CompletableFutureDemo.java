package org.example.repractice.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture.supplyAsync(() -> "Hello ")
                .thenApply(m -> m + "World from Chaining").thenAccept(System.out::println);

        CompletableFuture<String> combineFuture = completableFuture1
                .thenCombine(completableFuture2, (a, b) -> a + " " + b + " from Combine");

        System.out.println(combineFuture.get());

    }
}
