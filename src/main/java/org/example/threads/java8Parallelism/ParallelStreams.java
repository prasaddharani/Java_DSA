package org.example.threads.java8Parallelism;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreams {
    public static void main(String[] args) {
        List<Integer> data = IntStream.range(1, 100).boxed().toList();
        long start = System.currentTimeMillis();
        data.parallelStream().forEach(i -> {
            try {
                System.out.println("Thread Name: " + Thread.currentThread().getName() + " is processing with count " + i);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("Time taken with parallel stream: " + (end - start));

        start = System.currentTimeMillis();
        data.forEach(i -> {
            try {
                System.out.println("Thread Name: " + Thread.currentThread().getName() + " is processing with count " + i);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        end = System.currentTimeMillis();
        System.out.println("Time taken without parallel stream: " + (end - start));
    }
}
