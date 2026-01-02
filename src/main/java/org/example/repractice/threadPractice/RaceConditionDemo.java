package org.example.repractice.threadPractice;
class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public void printCounter() {
        System.out.println("Counter value: " + count);
    }
}
public class RaceConditionDemo {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            counter.printCounter();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
