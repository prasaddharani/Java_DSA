package org.example.repractice.designPatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private List<AccountObserver> observers;
    private double newBalance;
    public BankAccount() {
        this.observers = new ArrayList<>();
        this.newBalance = 0;
    }
    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    public void deposit(double amount) {
        newBalance += amount;
        notifyObservers();
    }

    public void notifyObservers() {
        observers.forEach(observer -> observer.update(newBalance));
    }
}
