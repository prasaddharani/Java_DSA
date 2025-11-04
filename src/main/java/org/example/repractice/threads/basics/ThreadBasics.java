package org.example.repractice.threads.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ThreadCreate1 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("Thread name: {} and processed index: {}", Thread.currentThread().getName(), i);
        }
    }
}

@Slf4j
class ThreadCreate2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("Thread name: {} and processed index: {}", Thread.currentThread().getName(), i);
        }
    }
}

@Slf4j
public class ThreadBasics {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadCreate1();
        Thread t2 = new ThreadCreate1();
        Runnable runnableThread = () -> {
            for (int i = 0; i < 5; i++) {
                log.info("Thread name: {} and processed index: {}", Thread.currentThread().getName(), i);
            }
        };
        Thread t3 = new Thread(runnableThread);
        Thread t4 = new Thread(new ThreadCreate2());
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Main Thread name: {}", Thread.currentThread().getName());
    }
}
