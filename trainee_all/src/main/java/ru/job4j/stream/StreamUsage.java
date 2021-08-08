package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(-5);
        list.add(-1);
        list.add(0);
        list.add(4);
        list.add(5);
        List<Integer> listFilter = new ArrayList<>();
        listFilter = list.stream().filter(item -> item > 0)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
