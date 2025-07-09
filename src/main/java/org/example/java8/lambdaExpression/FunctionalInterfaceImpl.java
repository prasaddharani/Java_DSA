package org.example.java8.lambdaExpression;

@FunctionalInterface
interface FunctionalInterfaceDemo {
    void sayHello(String name);
}

public class FunctionalInterfaceImpl {
    public static void main(String[] args) {
        FunctionalInterfaceDemo functionalInterface = (name) -> System.out.println("Hi " + name);
        functionalInterface.sayHello("Dharani");
    }
}
