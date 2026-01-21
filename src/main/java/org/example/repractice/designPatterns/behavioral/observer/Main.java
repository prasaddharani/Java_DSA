package org.example.repractice.designPatterns.behavioral.observer;

public class Main {
    public static void main(String[] args) {
        AccountObserver emailObserver = new EmailNotifier();
        AccountObserver fraudDetector = new FraudDetector();

        BankAccount bankAccount = new BankAccount();
        bankAccount.addObserver(emailObserver);
        bankAccount.addObserver(fraudDetector);

        bankAccount.deposit(1000);
    }
}
