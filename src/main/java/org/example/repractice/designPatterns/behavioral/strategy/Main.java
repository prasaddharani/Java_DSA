package org.example.repractice.designPatterns.behavioral.strategy;

public class Main {
    public static void main(String[] args) {
        PaymentStrategy creditCardPayment = new CreditCardPayment();
        PaymentStrategy debitCardPayment = new DebitCardPayment();
        PaymentStrategy stripePayment = new StripePayment();

        PaymentProcessor paymentProcessor = new PaymentProcessor(creditCardPayment);

        paymentProcessor.processPayment();

        paymentProcessor.setPaymentStrategy(debitCardPayment);
        paymentProcessor.processPayment();

        paymentProcessor.setPaymentStrategy(stripePayment);
        paymentProcessor.processPayment();
    }
}
