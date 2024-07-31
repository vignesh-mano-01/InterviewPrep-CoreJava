package com.interview.threads;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        InterruptibleLockExample example = new InterruptibleLockExample();

        Runnable task = () -> {
            example.performTask();
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000); // Let thread1 acquire the lock
            thread2.interrupt(); // Interrupt thread2 while it is waiting for the lock
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void performTask() {
        try {
            lock.lockInterruptibly(); // Attempt to acquire the lock, but be responsive to interruptions
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock." + "Time " + new Date().getTime());
                // Simulate long-running task
                Thread.sleep(5000);
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released the lock." + new Date().getTime());
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted." + new Date().getTime());
        }
    }
}