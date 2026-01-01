package org.example.repractice;

import java.util.function.Function;

public class Java8 {
    public static void main(String[] args) {
        Function<String, String> function = (a) -> "Hello " + a;
        System.out.println(function.apply("Dharani"));
    }
}
