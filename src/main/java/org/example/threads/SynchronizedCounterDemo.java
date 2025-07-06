package org.example.threads;

import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedCounterDemo {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        SynchronizedCounterDemo counterDemo = new SynchronizedCounterDemo();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counterDemo.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println("Final count: " + counterDemo.getCount());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
