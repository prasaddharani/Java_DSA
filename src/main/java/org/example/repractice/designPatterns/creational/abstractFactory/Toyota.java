package org.example.repractice.designPatterns.creational.abstractFactory;

public class Toyota implements Vehicle{
    @Override
    public void start() {
        System.out.println("Toyota car is starting");
    }

    @Override
    public void stop() {
        System.out.println("Toyota car is stopping");
    }
}
