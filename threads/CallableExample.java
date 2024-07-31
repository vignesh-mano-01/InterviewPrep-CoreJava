package com.interview.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Create a Callable task
        Callable<Integer> callableTask = new MyCallableTask(10);

        // Submit the Callable task to the ExecutorService
        Future<Integer> future = executorService.submit(callableTask);

        try {
            // Retrieve the result of the computation
            Integer result = future.get();
            System.out.println("Result of the callable task: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shut down the ExecutorService
            executorService.shutdown();
        }
    }
}
