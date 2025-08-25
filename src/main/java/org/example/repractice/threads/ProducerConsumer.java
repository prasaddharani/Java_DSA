package org.example.repractice.threads;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {
    static BlockingQueue<Integer> data = new LinkedBlockingDeque<>(5);
    public static void produce(int i) {
            try {
                System.out.println("Produced: " + i);
                data.put(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

    public static void consume() {
        try {
            System.out.println("Consumed: "+ data.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = () -> {
            for (int i = 0; i < 10; i++) {
               produce(i);
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 10; i++) {
                consume();
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
