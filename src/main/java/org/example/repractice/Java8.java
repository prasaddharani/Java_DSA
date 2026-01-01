package org.example.repractice;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class Java8 {
    public static void main(String[] args) {
        Function<String, String> function = (a) -> "Hello " + a;
        log.info("Function Example: {}", function.apply("Dharani"));

        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a * b;
        log.info("BiFunction Example: {}", biFunction.apply(2, 3));

        Consumer<String> consumer = (a) -> log.info("Hello " + a + " From Consumer Example");
        consumer.accept("Dharani");
    }
}
