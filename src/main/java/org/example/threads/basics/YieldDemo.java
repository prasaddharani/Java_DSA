package org.example.threads.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YieldDemo {
    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                log.info("Thread {} is running", Thread.currentThread().getName());
                Thread.yield();
                if (Thread.currentThread().getName().contains("Worker-2")) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        // Thread names
        t1.setName("Worker-1");
        t2.setName("Worker-2");

        t1.start();
        t2.start();

        // check thread is alive or not
        log.info("Thread status: {}", t1.isAlive());

        // when we call thread.interrupt() while thread is sleeping cause InterruptedExceptions
        t2.interrupt();

    }
}
