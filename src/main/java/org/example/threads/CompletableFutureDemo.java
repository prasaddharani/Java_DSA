package org.example.threads;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate a long-running task
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 42; // The result of the computation
        });

        future.thenAccept(result -> {
            System.out.println("The result is: " + result);
        }).exceptionally(ex -> {
            System.err.println("An error occurred: " + ex.getMessage());
            return null;
        });

        // Real-time example: Fetch user profile and order history in parallel, then combine results
        CompletableFuture<String> userProfileFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate API/database call
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "User: Alice";
        });

        CompletableFuture<String> orderHistoryFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500); // Simulate another API/database call
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Orders: [Book, Pen]";
        });

        userProfileFuture.thenCombine(orderHistoryFuture, (profile, orders) -> profile + ", " + orders)
                .thenAccept(summary -> System.out.println("Summary: " + summary));

        // Keep the main thread alive to see the result
        try {
            Thread.sleep(3000); // Wait for the CompletableFuture to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
