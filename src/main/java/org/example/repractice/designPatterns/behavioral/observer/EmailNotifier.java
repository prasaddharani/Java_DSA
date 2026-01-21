package org.example.repractice.designPatterns.behavioral.observer;

public class EmailNotifier implements AccountObserver{
    @Override
    public void update(double newBalance) {
        System.out.println("Amount credited. New Balance is: " + newBalance);
    }
}
