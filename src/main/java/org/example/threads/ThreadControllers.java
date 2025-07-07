package org.example.threads;

public class ThreadControllers {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("T1: Count " + i);            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            for (char i = 65; i < 70; i++) {
                System.out.println("T2: Char " + i);            }
        });

        t1.start();
        t2.start();
        t2.join();

    }
}
