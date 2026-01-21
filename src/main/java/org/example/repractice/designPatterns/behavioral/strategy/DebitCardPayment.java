package org.example.repractice.designPatterns.behavioral.strategy;

public class DebitCardPayment implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("Debit card payment");
    }
}
