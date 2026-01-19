package org.example.repractice.solid.singleton;

public class LazySingleton {

    private static LazySingleton INSTANCE = null;

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }
}
