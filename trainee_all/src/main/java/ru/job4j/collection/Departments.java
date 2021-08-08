package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = "";
            for (String el : value.split("/")) {
                start += el;
                tmp.add(start);
                start += "/";
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        orgs.sort(Comparator.naturalOrder());
        System.out.println(orgs);
    }

    public static void sortDesc(List<String> orgs) {
        orgs.sort(new DepDescComp());
        System.out.println(orgs);
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("k1", "k1/sk2", "k1/sk1",
                "k1/sk1/ssk1", "k2", "k2/sk2", "k2/sk1");
        sortAsc(input);
        System.out.println();
        sortDesc(input);
    }
}
