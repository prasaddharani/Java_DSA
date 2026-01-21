package org.example.repractice.designPatterns.behavioral.observer;

public class FraudDetector implements AccountObserver {
    @Override
    public void update(double newBalance) {
        System.out.println("Fraud Detector: Balance changed to " + newBalance);
    }
}
