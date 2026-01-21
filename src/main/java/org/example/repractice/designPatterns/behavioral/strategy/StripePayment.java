package org.example.repractice.designPatterns.behavioral.strategy;

public class StripePayment implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("Stripe Payment");
    }
}
