package org.example.java8;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class OptionalDemo {
    public static void main(String[] args) {
        // creating Optionals
        Optional<String> name1 = Optional.of("Dharani"); // Non-null only
        Optional<String> name2 = Optional.empty(); // empty
        Optional<String> name3 = Optional.ofNullable(null); // can be null
        log.info("name1 {}", name1.isPresent());
        log.info("name1 get {}", name1.get());
        log.info("name3 get {}", name3.orElse("Prasad"));
        name1.ifPresent(System.out::println);
        name1.map(String::toUpperCase);
    }
}
