package org.example.repractice.threadPractice;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    public void print() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Runnable r = demo::print;

        new Thread(r, "T1").start();
        new Thread(r, "T2").start();
    }
}