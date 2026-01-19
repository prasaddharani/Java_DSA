package org.example.repractice.singleton;

public class SingleLockSingleton {
    private static SingleLockSingleton INSTANCE = null;

    public static SingleLockSingleton getInstance() {
        synchronized (SingleLockSingleton.class) {
            if (INSTANCE == null) {
                INSTANCE = new SingleLockSingleton();
            }
        }
        return INSTANCE;
    }
}
