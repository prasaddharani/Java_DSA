package org.example.threads;

public class RunnableExample implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnableExample(), "Thread 1");
        Thread thread2 = new Thread(new RunnableExample(), "Thread 2");

        thread1.start(); // reason for not directly calling run() method is because it would not start a new thread,
                        // it would just execute the run method in the current thread context.
        thread2.start();

        try {
            thread1.join(); // Wait for thread1 to finish
            thread2.join(); // Wait for thread2 to finish
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }

        System.out.println("Both threads have finished execution.");
    }
}
