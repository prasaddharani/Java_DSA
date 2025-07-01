package org.example.threads;

public class ThreadSynchronization extends Thread {

    private static final Object lock = new Object();
    private static int sharedCounter = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                sharedCounter++;
                System.out.println(Thread.currentThread().getName() + " - Shared Counter: " + sharedCounter);
            }
            try {
                Thread.sleep(500); // Sleep for 0.5 seconds
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ThreadSynchronization thread1 = new ThreadSynchronization();
        ThreadSynchronization thread2 = new ThreadSynchronization();

        thread1.setName("Thread 1");
        thread2.setName("Thread 2");

        thread1.start();
        thread2.start();

        try {
            thread1.join(); // Wait for thread1 to finish
            thread2.join(); // Wait for thread2 to finish
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }

        System.out.println("Both threads have finished execution.");
    }
}
