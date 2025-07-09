package org.example.java8.lambdaExpression;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.BiFunction;

public class LambdaDemo {
    public static void main(String[] args) {
        // basic example
        Runnable r = () -> System.out.println("Runnable Interface");
        new Thread(r).start();

        // with parameters
        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
        // can be simplified with method reference
        comparator = Integer::compareTo;
        System.out.println(comparator.compare(6, 5));

        // with block body
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> {
            System.out.println("Adding Numbers");
            return a + b;
        };
        System.out.println("BiFunction: " + biFunction.apply(2, 3));

        // can throw unchecked exception without handling
        Runnable r1 = () -> {
            throw new RuntimeException();
        };

        // however need to handle explicitly for checked exception either via try/catch
        Runnable r2 = () -> {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

    }
}
