package org.example.java8.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamDemo {
    public static void main(String[] args) {
        // filter name starts with J and make it Upper case
        List<String> names = List.of("Dharani", "Prasad", "Jai", "John");
        names.stream()
                .filter(name -> name.startsWith("J"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
        // collecting results
        List<String> result =
                names.stream()
                        .map(String::toUpperCase)
                        .toList();
        log.info("Collecting result: {}", result);

        // Reduce
        List<Integer> numbers = IntStream.range(1, 5).boxed().toList();
        int num = numbers.stream().reduce(0, (a, b) -> a + b);
        log.info("Reduced result {}", num);

        // Count and Distinct
        List<Integer> distinctNumbers = List.of(1, 2, 3, 3, 4, 4, 5);
        long count = distinctNumbers.stream().distinct().count();
        log.info("distinct count {}", count);


    }
}
