package org.example.interview.singleton;

public class LazySingleton {
    private static LazySingleton instance = null;

    LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
