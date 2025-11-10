package org.example.interview;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ScenarioBased {
    public static void main(String[] args) throws IOException {
        // Can we have try block without catch?
        try {
            log.info("try block");
        } finally {
            log.info("Ran fine without catch block");
        }

        // But there is try-with-resources which will closes the connection automatically
        try (FileReader file = new FileReader("C:\\Users\\dhara\\IdeaProjects\\javaPractice\\src\\main\\java\\org\\example\\basics\\regexp.md")) {
            log.info("Reading a file using try-with-resources");
        }

        // Return statement in try and finally block which one returns?
        int value = returnWithTryAndFinally();
        log.info("Returned value from try and finally: {}", value);

        // Override literal value in finally and what try will return?
        int xValue = returnX();
        log.info("Returned X Value: {}", xValue);

        // Nested try block
        try {
            log.info("Parent try block");
            try {
                log.info("Child try block");
            } finally {
                log.info("Child finally block");
            }
        } finally{
            log.info("Parent finally block");
        }

        //When finally will not execute?
        //exitWithoutFinally();

        // Concurrent modification exception
        //arrayRemovalWhileIteration();

        // Can we have switch statement which has null as condition
        //nullSwitchCondition();

        // Diamond problem
        Child c = new Child();
        c.hello();

    }

    private static int returnWithTryAndFinally() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    private static int returnX() {
        int x = 1;
        try {
            return x;
        } finally {
            x = 2;
        }
    }

    private static void exitWithoutFinally() {
        try {
            System.exit(0);
        } finally {
            log.info("This will not execute");
        }
    }

    private static void arrayRemovalWhileIteration() {
        List<Integer> data = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        for (Integer val: data) {
            if (val == 2) {
                data.remove(val);
            }
        }
    }

    private static void nullSwitchCondition() {
        String data = "string";
        switch (data) {
            case "string" -> log.info("matched");
            case null -> log.info("null supported");
            //case null: // compilation error < 21 Java version 
            default -> throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}

// Diamond problem
interface A {
    default void hello() {
        System.out.println("Hello from interface-A");
    }
}

interface B {
    default void hello() {
        System.out.println("Hello from interface-B");
    }
}

class Child implements A, B {

    @Override
    public void hello() {
        A.super.hello();
    }
}