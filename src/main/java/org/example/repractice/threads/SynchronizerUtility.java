package org.example.repractice.threads;

import java.util.concurrent.*;

class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 9; i++) {
            int carId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Car is parked with id: " + carId);
                    Thread.sleep(2000);
                    System.out.println("Car is leaving with id: " + carId);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}

class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(10);

        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            new Thread(() -> {
                System.out.println("Task 1 is completed for id: " + taskId);
                phaser.arriveAndAwaitAdvance();
                System.out.println("Task 2 is completed for id: " + taskId);
                phaser.arriveAndAwaitAdvance();
            }).start();
        }
    }
}

class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String msg;
                try {
                    msg = exchanger.exchange("Msg from Thread-A with id: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread A Message: " + msg);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String msg;
                try {
                    msg = exchanger.exchange("Msg from Thread-B with id: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread B Message: " + msg);
            }
        }).start();
    }
}

class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            int taskId = i;
            new Thread(() -> {
                System.out.println("Task with id: " + taskId + " is completed");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("All workers finished. Proceeding...");
        for (int i = 4; i < 7; i++) {
            int taskId = i;
            new Thread(() -> {
                System.out.println("Task with id: " + taskId + " is completed");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }
}

class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("Player with id: " + finalI + " is ready for race");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Player with id: " + finalI + " started running");
            }).start();
        }
    }
}
public class SynchronizerUtility {
}
