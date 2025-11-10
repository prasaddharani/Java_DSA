package org.example.interview.singleton;

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance = null;

    ThreadSafeSingleton() {}

    public synchronized static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
