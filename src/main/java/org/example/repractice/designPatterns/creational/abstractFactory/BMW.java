package org.example.repractice.designPatterns.creational.abstractFactory;

public class BMW implements Vehicle{
    @Override
    public void start() {
        System.out.println("BMW car is starting");
    }

    @Override
    public void stop() {
        System.out.println("BMW car is stopping");
    }
}
