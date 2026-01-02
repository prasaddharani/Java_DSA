package org.example.repractice.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ConsumerDemo implements Runnable {
    BlockingQueue<String> queue;

    ConsumerDemo(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = queue.take();
                System.out.println("Consumed: "+ data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class ProducerDemo implements Runnable{
    BlockingQueue<String> queue;

    ProducerDemo(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("Produced: Data-" + i);
                    queue.put("Data-" + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        new Thread(new ProducerDemo(queue)).start();
        new Thread(new ConsumerDemo(queue)).start();
    }
}
