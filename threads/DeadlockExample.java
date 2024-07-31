package com.interview.threads;

public class DeadlockExample {
    // Resource 1
    private static final Object resource1 = new Object();
    // Resource 2
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        // Thread 1
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");

                // Adding a delay so that Thread 2 can lock resource 2
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Waiting to lock resource 2");
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked resource 2");

                // Adding a delay so that Thread 1 can lock resource 1
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Waiting to lock resource 1");
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();
    }
}

