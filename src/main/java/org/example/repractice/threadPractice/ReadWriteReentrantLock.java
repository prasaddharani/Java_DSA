package org.example.repractice.threadPractice;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteReentrantLock {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int value = 0;

    public void read() {
        try {
            readWriteLock.readLock().lock();
            System.out.println("value: " + value);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void write(int data) {
        try {
            readWriteLock.writeLock().lock();
            value = data;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
