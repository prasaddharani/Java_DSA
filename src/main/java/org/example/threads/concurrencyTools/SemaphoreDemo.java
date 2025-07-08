package org.example.threads.concurrencyTools;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        Runnable task = () -> {
            try {
                semaphore.acquire(); // acquire permit
                System.out.println(Thread.currentThread().getName() + " acquired permit");
                Thread.sleep(1000);
            } catch (Exception ignored) {

            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing permit");
                semaphore.release(); // release permit
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}
