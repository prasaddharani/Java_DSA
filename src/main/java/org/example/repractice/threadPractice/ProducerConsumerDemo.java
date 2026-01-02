package org.example.repractice.threadPractice;

class SharedResource {
    private volatile boolean isAvail = false;
    private int data;

    public synchronized void produce(int i) {
        while (isAvail) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        data = i;
        System.out.println("Produced data: " + data);
        isAvail = true;
        notify();
    }

    public synchronized void consume() {
        while (!isAvail) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Consumed data: " + data);
        data = 0;
        isAvail = false;
        notify();
    }
}
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sharedResource.produce(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sharedResource.consume();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
