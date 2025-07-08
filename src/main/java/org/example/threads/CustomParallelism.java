package org.example.threads;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class CustomParallelism {
    public static void main(String[] args) {
        ForkJoinPool customPool = new ForkJoinPool(4);
        customPool.submit(() -> {
            IntStream.range(1, 100).boxed().parallel().forEach(i -> {
                System.out.println(Thread.currentThread().getName() + " is Thread Name with count : " + i);
            });
        }).join();

        customPool.submit(() -> {
            IntStream.range(1, 100).boxed().parallel().forEachOrdered(i -> {
                System.out.println(Thread.currentThread().getName() + " is Thread Name with count : " + i);
            });
        }).join();

        IntStream.range(1, 100).boxed().parallel().forEachOrdered(i -> {
            System.out.println(Thread.currentThread().getName() + " is Thread Name with count : " + i);
        });

        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallelStream().forEachOrdered(i -> {
            System.out.println(Thread.currentThread().getName() + " is Thread Name with count : " + i);
        });

    }
}
