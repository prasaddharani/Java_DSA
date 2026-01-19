package org.example.repractice.designPatterns.creational.factory;

public class Car implements Vehicle{
    @Override
    public void start() {
        System.out.println("Car is started");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopped");
    }
}
