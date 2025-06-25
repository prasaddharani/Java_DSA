package org.example.fundamentals;

public class StaticExample {
    public static final int STATIC_VAR = 1;
    private final int NON_STATIC_VAR = 2;
    public static void staticMethod() {
        int result = STATIC_VAR + new StaticExample().NON_STATIC_VAR;
        System.out.println("This is static method with var " + result);
    }

    public void instanceMethod() {
        int result = STATIC_VAR + NON_STATIC_VAR;
        System.out.println("This is instance method with var " + result);
    }
}

class App {
    public static void main(String[] args) {
        StaticExample.staticMethod();
        new StaticExample().instanceMethod();
    }
}
