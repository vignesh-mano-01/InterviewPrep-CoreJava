package com.interview.collections;

import java.util.*;

public class CollectionsExamples {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("d");

        List list2 = new ArrayList();
        list2.add("a");
        list2.add("b");
        list2.addAll(list);

        Deque stack = new LinkedList();
        Set set = new LinkedHashSet();
        System.out.println(list.containsAll(list2));
    }
}
