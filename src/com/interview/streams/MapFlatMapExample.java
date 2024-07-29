package com.interview.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapFlatMapExample {

    public static void main(String[] args) {
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e"),
                Arrays.asList("f", "g", "h")
        );

        List<String> flatList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(flatList);
    }



}
