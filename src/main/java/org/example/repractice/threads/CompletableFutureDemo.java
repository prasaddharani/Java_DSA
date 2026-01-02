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

        CompletableFuture<String> returnsValue = CompletableFuture.supplyAsync(() -> "returns value");
        CompletableFuture<Void> returnsNothing = CompletableFuture.runAsync(() -> System.out.println("returns nothing"));
        System.out.println(returnsValue.get());

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> System.out.println("Task-1")),
                CompletableFuture.runAsync(() -> System.out.println("Task-2")),
                CompletableFuture.runAsync(() -> System.out.println("Task-3")));
        allTasks.join();

        CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException();
            }
            return "Hi";
        }).exceptionally(ex -> "Recovered from exception: " + ex.getMessage())
                .thenAccept(System.out::println);
    }
}
