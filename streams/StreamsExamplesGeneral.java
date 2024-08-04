package com.interview.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StreamsExamplesGeneral {

    public static void main(String[] args) {

        // IntStream
        IntStream range = IntStream.range(1, 100);
        OptionalInt max = range.max();
        //System.out.println(max);

        //frequency of number
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        //System.out.println(Collections.frequency(list, 1));

        //Count occurrence of a given character in a string using Stream API in Java
        String s = "test";
        System.out.println(s.chars().filter(c -> c == 't').count());
    }
}
