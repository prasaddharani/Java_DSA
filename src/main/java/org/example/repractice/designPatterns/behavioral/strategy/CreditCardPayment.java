package org.example.repractice.designPatterns.behavioral.strategy;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("Credit card payment");
    }
}
