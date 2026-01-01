package org.example.repractice;

import lombok.extern.slf4j.Slf4j;

import java.util.function.*;

@Slf4j
public class Java8 {
    public static void main(String[] args) {
        Function<String, String> function = (a) -> "Hello " + a;
        log.info("Function Example: {}", function.apply("Dharani"));

        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a * b;
        log.info("BiFunction Example: {}", biFunction.apply(2, 3));

        Consumer<String> consumer = (a) -> log.info("Hello " + a + " From Consumer Example");
        consumer.accept("Dharani");

        BiConsumer<String, String> biConsumer = (a, b) ->
                log.info("Hello {} {} from BiConsumer Example", a, b);
        biConsumer.accept("Dharani", "Prasad");

        Supplier<String> supplier = () -> "Hello From Supplier";
        log.info(supplier.get());

        Predicate<Integer> predicate = a -> a % 2 == 0;
        log.info("Predicate Example: {}", predicate.test(2));

        BiPredicate<String, String> biPredicate = String::equalsIgnoreCase; // equivalent to (a, b) -> a.equalsIgnoreCase(b);
        log.info("BiPredicate Example: {}", biPredicate.test("Hello", "hello"));


        // Primitive Specialization => Avoid AutoBoxing and boost performance
        IntUnaryOperator unaryOperator = (a) -> a * a;
        log.info("UnaryOperator Example: {}", unaryOperator.applyAsInt(2));

        // Same applicable for IntPredicate, IntConsumer, IntSupplier

        // Chaining
        Function<Integer, Integer> doubleIt = (a) -> a * 2;
        Function<Integer, Integer> squareIt = a -> a * a;

        Function<Integer, Integer> result = doubleIt.andThen(squareIt);
        log.info("Chaining example: {}", result.apply(2));

    }
}
