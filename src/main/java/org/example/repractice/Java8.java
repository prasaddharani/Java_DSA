package org.example.repractice;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Java8 {
    public static void main(String[] args) {
        Function<String, String> function = (a) -> "Hello " + a;
        System.out.println("Function Example: " + function.apply("Dharani"));

        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a * b;
        System.out.println("BiFunction Example: " + biFunction.apply(2, 3));


    }
}
