package org.example.repractice.singleton;

public class LazySingleton {

    private static LazySingleton INSTANCE = null;

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }
}
