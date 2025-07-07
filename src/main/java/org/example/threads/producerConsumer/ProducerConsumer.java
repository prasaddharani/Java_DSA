package org.example.threads.producerConsumer;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
       SharedBuffer buffer = new SharedBuffer();

       Thread t1 = new Thread(() -> {
           for (int i = 0; i < 5; i++) {
               try {
                   buffer.produce(i);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
