package org.example.java8;

import java.util.function.*;

public class BuiltInFunctionalInterface {
    public static void main(String[] args) {
        // Function takes one input and return an output
        Function<String, Integer> function = String::length; // input -> input.length()
        System.out.println("Function example " + function.apply("Dharani"));

        // Predicate returns True or False
        Predicate<Integer> predicate = integer -> integer % 2 == 0;
        System.out.println("Predicate example " + predicate.test(2));

        // BiFunction Takes 2 input and provides single output
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) ->  a > b ? a: b;
        System.out.println("BiFunction Example " + biFunction.apply(2, 4));

        // Consumer takes one input and returns nothing means void
        Consumer<String> consumer = string -> System.out.println("Hi " + string);
        consumer.accept("Dharani");

        // Supplier takes nothing but returns single input
        Supplier<String> supplier = () -> "Supplier";
        System.out.println("Hi from " + supplier.get());
    }
}
