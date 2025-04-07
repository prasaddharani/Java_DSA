package main.resources.fundamentals;

public class ExecutionFlow {
    static {
        System.out.println("Static block 1 executed");
    }

    static {
        System.out.println("Static block 2 executed");
    }

    {
        System.out.println("Instance block 1 executed");
    }

    {
        System.out.println("Instance block 2 executed");
    }

    public ExecutionFlow() {
        System.out.println("Constructor called");
    }

    public static void main(String[] args) {
        System.out.println("Main method started");
        new ExecutionFlow();
        new ExecutionFlow();
        System.out.println("Main method ended");
    }
}


