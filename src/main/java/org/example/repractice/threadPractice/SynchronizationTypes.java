package org.example.repractice.threadPractice;

import java.nio.file.Path;

class SynchronizedCounter {
    private int count = 0;
    private static int staticCount = 0;

    public synchronized void increment() {
        count++;
    }

    public void blockLevelIncrement() {
        synchronized (this) {
            count++;
        }
    }

    public static synchronized void staticIncrement() {
        staticCount++;
    }

    public static void blockClassLevelIncrement() {
        synchronized (SynchronizedCounter.class) {
            staticCount++;
        }
    }

    public void printCounter() {
        System.out.println("Counter value: " + count);
    }

    public void printStaticCounter() {
        System.out.println("Static Counter Value: " + staticCount);
    }
}

public class SynchronizationTypes {

    public static void main(String[] args) {
        SynchronizedCounter counter = new SynchronizedCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.blockLevelIncrement();
                SynchronizedCounter.staticIncrement();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.blockLevelIncrement();
                SynchronizedCounter.staticIncrement();
            }
        });

        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
            counter.printCounter();
            counter.printStaticCounter();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
