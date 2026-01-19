package org.example.repractice.designPatterns.creational.factory;

public class Bike implements Vehicle {

    @Override
    public void start() {
        System.out.println("Bike is starting");
    }

    @Override
    public void stop() {
        System.out.println("Bike is stopping");
    }
}
