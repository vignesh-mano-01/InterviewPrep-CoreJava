package com.interview.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindAnyParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        for (int i = 0; i < 20; i++) {
            int result = numbers.parallelStream()
                    .filter(n -> n > 5)
                    .findAny()
                    .orElse(-1);
            System.out.println("Result: " + result);
        }
    }
}
