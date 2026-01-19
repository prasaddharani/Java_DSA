package org.example.repractice.solid.singleton;

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
