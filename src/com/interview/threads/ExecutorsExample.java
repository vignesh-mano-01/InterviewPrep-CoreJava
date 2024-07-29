package com.interview.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new MyRunnable();
            executor.execute(worker);
        }
        executor.shutdown();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        });
    }
}

