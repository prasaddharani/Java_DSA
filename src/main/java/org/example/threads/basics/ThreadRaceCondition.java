package org.example.threads.basics;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

class Counter {
    int count = 0;

    public void increment() {
        synchronized (Counter.class) {
            count++;
        }
    }
}

class ReentrantCounter {
    @Getter
    private int count = 0;
    private final ReentrantLock reentrantLock;

    ReentrantCounter(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    public void increment() {
        reentrantLock.lock();
        try {
            count++;
        } finally {
            reentrantLock.unlock();
        }
    }
}

@Slf4j
public class ThreadRaceCondition {
    public static void main(String[] args) {

        Counter c = new Counter();
        Runnable r = () -> {
            for (int i = 0; i < 2000; i++) {
                c.increment();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            log.info("Exception raised: {}", ex.getMessage());
        }

        log.info("Counter value: {}", c.count);
    }


}

@Slf4j
class ReentrantLockMain {
    public static void main(String[] args) throws InterruptedException {
        ReentrantCounter reentrantCounter = new ReentrantCounter(new ReentrantLock());

        Runnable r = () -> {
            for (int i = 0; i < 2000; i++) {
                reentrantCounter.increment();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.info("Counter value with ReentrantLock: {}", reentrantCounter.getCount());
    }
}
