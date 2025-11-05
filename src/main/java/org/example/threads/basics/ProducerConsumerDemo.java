package org.example.threads.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SharedResource {
    private boolean isAvail = false;
    private int data;

    public synchronized void produce (int i) {
        try {
            while (isAvail) {
                wait();
            }
            data = i;
            isAvail = true;
            log.info("Produced item: {}", data);
            notify();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void consume () {
        try {
            while (!isAvail) {
                wait();
            }
            log.info("Consumed item: {}", data);
            data = 0;
            isAvail = false;
            notify();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

@Slf4j
public class ProducerConsumerDemo {

    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();

        Runnable r1 = () -> {
            for (int i = 0; i < 5; i++) {
                resource.produce(i);
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 5; i++) {
                resource.consume();
            }
        };

        Thread t1 = new Thread(r1, "Producer-Worker");
        Thread t2 = new Thread(r2, "Consumer-Worker");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
