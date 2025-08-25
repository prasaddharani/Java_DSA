package org.example.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import java.util.concurrent.Phaser;

import java.util.concurrent.CyclicBarrier;

class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 3;
        CountDownLatch latch = new CountDownLatch(tasks);

        for (int i = 1; i <= tasks; i++) {
            int workerId = i;
            new Thread(() -> {
                System.out.println("Worker " + workerId + " finished task.");
                latch.countDown(); // decrease the latch count
            }).start();
        }

        latch.await(); // main thread waits until count=0
        System.out.println("All workers finished. Proceeding...");
    }
}


class CyclicBarrierExample {
    public static void main(String[] args) {
        int players = 3;
        CyclicBarrier barrier = new CyclicBarrier(players,
                () -> System.out.println("All players arrived. Game starts!"));

        for (int i = 1; i <= players; i++) {
            int playerId = i;
            new Thread(() -> {
                System.out.println("Player " + playerId + " reached lobby.");
                try {
                    barrier.await(); // wait for others
                } catch (Exception ignored) {}
                System.out.println("Player " + playerId + " is playing.");
            }).start();
        }
    }
}


 class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3); // 3 parties

        for (int i = 1; i <= 3; i++) {
            int id = i;
            new Thread(() -> {
                System.out.println("Thread " + id + " Phase 1 complete");
                phaser.arriveAndAwaitAdvance(); // wait for others

                System.out.println("Thread " + id + " Phase 2 complete");
                phaser.arriveAndAwaitAdvance();
            }).start();
        }
    }
}


public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 1; i < 10; i ++) {
            int carId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Car " + carId + " parked.");
                    Thread.sleep(2000); // parking time
                    System.out.println("Car " + carId + " leaving.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
