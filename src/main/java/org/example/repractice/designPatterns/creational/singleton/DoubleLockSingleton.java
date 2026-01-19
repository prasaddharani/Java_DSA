package org.example.repractice.designPatterns.creational.singleton;

public class DoubleLockSingleton {
    private static DoubleLockSingleton INSTANCE = null;

    public static DoubleLockSingleton getInstance() {
            if (INSTANCE == null) {
                synchronized (DoubleLockSingleton.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new DoubleLockSingleton();
                    }
                }
            }
            return INSTANCE;
        }
}
