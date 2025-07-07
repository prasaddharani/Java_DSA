package org.example.threads.producerConsumer;

public class SharedBuffer {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) {
            wait();
        }
        data = value;
        System.out.println("Produced: " + value);

        hasData = true;
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasData) {
            wait();
        }
        System.out.println("Consumed: " + data);

        hasData = false;
        notify();
    }
}
