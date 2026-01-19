package org.example.repractice.solid.singleton;

public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
