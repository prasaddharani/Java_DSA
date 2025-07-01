package org.example.threads;

public class ThreadStates {
    private static final Object lock = new Object();

    static class BlockedThread extends Thread {
        public void run() {
            synchronized (lock) {
                System.out.println(getName() + ": Acquired lock, now sleeping (holding lock)...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(getName() + ": Interrupted.");
                }
                System.out.println(getName() + ": Done.");
            }
        }
    }

    static class WaitingThread extends Thread {
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println(getName() + ": Calling wait(), entering WAITING state...");
                    lock.wait();
                    System.out.println(getName() + ": Notified and running again.");
                } catch (InterruptedException e) {
                    System.out.println(getName() + ": Interrupted.");
                }
            }
        }
    }

    static class TimedWaitingThread extends Thread {
        public void run() {
            try {
                System.out.println(getName() + ": Sleeping for 2 seconds (TIMED_WAITING)...");
                Thread.sleep(2000);
                System.out.println(getName() + ": Woke up.");
            } catch (InterruptedException e) {
                System.out.println(getName() + ": Interrupted.");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // NEW state
        Thread blocked = new BlockedThread();
        Thread waiting = new WaitingThread();
        Thread timedWaiting = new TimedWaitingThread();
        System.out.println(blocked.getName() + " is in NEW state: " + blocked.getState());
        System.out.println(waiting.getName() + " is in NEW state: " + waiting.getState());
        System.out.println(timedWaiting.getName() + " is in NEW state: " + timedWaiting.getState());

        // Start BlockedThread and let it acquire the lock
        blocked.start();
        Thread.sleep(100); // Ensure blocked acquires the lock

        // Start WaitingThread (will BLOCK until lock is free)
        waiting.start();
        Thread.sleep(100); // Give time to attempt lock
        System.out.println(waiting.getName() + " should be BLOCKED: " + waiting.getState());

        // Start TimedWaitingThread
        timedWaiting.start();
        Thread.sleep(100); // Let it enter sleep
        System.out.println(timedWaiting.getName() + " should be TIMED_WAITING: " + timedWaiting.getState());

        // After BlockedThread releases lock, WaitingThread will acquire and call wait()
        blocked.join();
        Thread.sleep(100); // Give time for waiting to call wait()
        System.out.println(waiting.getName() + " should be WAITING: " + waiting.getState());

        // Notify waiting thread
        synchronized (lock) {
            lock.notify();
        }
        waiting.join();
        timedWaiting.join();

        // TERMINATED state
        System.out.println(blocked.getName() + " is TERMINATED: " + blocked.getState());
        System.out.println(waiting.getName() + " is TERMINATED: " + waiting.getState());
        System.out.println(timedWaiting.getName() + " is TERMINATED: " + timedWaiting.getState());
    }
}
