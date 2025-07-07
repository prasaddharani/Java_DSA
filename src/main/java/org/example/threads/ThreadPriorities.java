package org.example.threads;

public class ThreadPriorities {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread is running : " + Thread.currentThread().getName() + "with count "+i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread is running : " + Thread.currentThread().getName() + "with count "+i);
            }
        });

        t1.setPriority(10);
        t2.setPriority(2);
        t1.setName("High_Priority_Thread");
        t2.setName("Low_Priority_Thread");

        t2.start();
        t1.start();
        t1.join();
        t2.join();

    }
}
