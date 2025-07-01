package org.example.threads;

public class ThreadNotify extends Thread {
    private static final Object lock = new Object();
    private static int sharedCounter = 0;

    static class CounterThread extends Thread {
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    sharedCounter++;
                    System.out.println(getName() + ": incremented counter to " + sharedCounter);
                    if (sharedCounter == 5) {
                        // lock.notify() // It will notify only one waiting thread and not all
                        lock.notifyAll(); // Notify waiting thread when counter reaches 5
                    }
                    try {
                        Thread.sleep(200); // Simulate work
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class WaitingThread extends Thread {
        public void run() {
            synchronized (lock) {
                while (sharedCounter < 5) {
                    try {
                        System.out.println(getName() + ": waiting for counter to reach 5...");
                        lock.wait(); // Wait until notified
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(getName() + ": detected counter reached 5!");
            }
        }
    }

    static class WaitingThread2 extends Thread {
        public void run() {
            synchronized (lock) {
                while (sharedCounter < 5) {
                    try {
                        System.out.println(getName() + ": waiting for counter to reach 5...");
                        lock.wait(); // Wait until notified
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(getName() + ": detected counter reached 5!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitingThread waitingThread = new WaitingThread();
        WaitingThread2 waitingThread2 = new WaitingThread2();
        CounterThread counterThread = new CounterThread();
        waitingThread.start();
        waitingThread2.start();
        Thread.sleep(100); // Ensure waitingThread starts and waits
        counterThread.start();
        waitingThread.join();
        waitingThread2.join();
        counterThread.join();
        System.out.println("Final counter value: " + sharedCounter);
    }
}
