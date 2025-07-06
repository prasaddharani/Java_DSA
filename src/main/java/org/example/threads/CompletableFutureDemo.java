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

        // Advanced real-world example: Chaining async calls with error handling
        CompletableFuture<String> chainedExample = CompletableFuture.supplyAsync(() -> {
            // Simulate fetching user profile
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "User: Bob";
        }).thenCompose(profile -> CompletableFuture.supplyAsync(() -> {
            // Simulate fetching recommendations based on profile
            if (profile.contains("Bob")) {
                // Simulate an error for demonstration
                throw new RuntimeException("Failed to fetch recommendations for Bob");
            }
            return "Recommendations: [Laptop, Mouse]";
        })).exceptionally(ex -> {
            // Handle any exception in the chain
            System.err.println("Error in chained async call: " + ex.getMessage());
            return "No recommendations available";
        });

        // Print the final result of the chained example
        chainedExample.thenAccept(result -> System.out.println("Chained result: " + result));

        // Keep the main thread alive to see the result
        try {
            Thread.sleep(3000); // Wait for the CompletableFuture to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
