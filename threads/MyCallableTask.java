package com.interview.threads;

import java.util.concurrent.Callable;

public class MyCallableTask implements Callable<Integer> {
    private int i;

    public MyCallableTask(int i) {
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        return i;
    }
}
