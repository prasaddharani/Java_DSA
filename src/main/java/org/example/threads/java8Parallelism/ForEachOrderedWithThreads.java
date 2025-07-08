package org.example.threads.java8Parallelism;

import java.util.stream.IntStream;

public class ForEachOrderedWithThreads {
    public static void main(String[] args) {
        System.out.println("forEach (unordered):");
        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " => " + i);
                });

        System.out.println("\nforEachOrdered (ordered):");
        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEachOrdered(i -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " => " + i);
                });

        long start = System.currentTimeMillis();
        IntStream.rangeClosed(1, 1000)
                .forEachOrdered(i -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " => " + i);
                });
        long end = System.currentTimeMillis();
        System.out.println("Time taken to process without parallel " + (end - start));

        start = System.currentTimeMillis();
        IntStream.rangeClosed(1, 1000)
                .parallel()
                .forEachOrdered(i -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " => " + i);
                });
        end = System.currentTimeMillis();
        System.out.println("Time taken to process with parallel " + (end - start));
    }
}
