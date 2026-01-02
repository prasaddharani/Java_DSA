package org.example.repractice.threadPractice;

class A extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("method from A is running: " + i);
        }
    }
}

class B implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("method from B is running: " + i);
        }
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        Thread a = new A();
        Thread b = new Thread(new B());

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
