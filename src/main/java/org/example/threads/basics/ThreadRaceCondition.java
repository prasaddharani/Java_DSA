package org.example.threads.basics;

import lombok.extern.slf4j.Slf4j;

class Counter {
    int count = 0;

    public synchronized void increment() {
        count++;
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
