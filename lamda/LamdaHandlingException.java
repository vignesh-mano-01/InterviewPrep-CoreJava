package com.interview.lamda;

import java.util.function.BiConsumer;

public class LamdaHandlingException {


    public static void main(String[] args) {
        int[] someNumbers = {1, 2, 3, 4};
        int key = 0;
        process(someNumbers, key, wrapperLamda((v, k) -> System.out.println(v / k)));
    }

    private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
        for (int i : someNumbers) {
            consumer.accept(i, key);
        }
    }

    private static BiConsumer<Integer, Integer> wrapperLamda(BiConsumer<Integer, Integer> biconsumer) {
        return (v, k) -> {
            try {
                biconsumer.accept(v, k);
            } catch (ArithmeticException ex) {
                System.out.println("Exception caught in lamda expression");
            }
        };
    }
}
