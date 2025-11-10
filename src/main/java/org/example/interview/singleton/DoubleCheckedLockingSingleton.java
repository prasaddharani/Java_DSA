package org.example.interview.singleton;

public class DoubleCheckedLockingSingleton {
    private static DoubleCheckedLockingSingleton instance = null;

    DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
