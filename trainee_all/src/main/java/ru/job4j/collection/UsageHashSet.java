package ru.job4j.collection;

import java.util.HashSet;

public class UsageHashSet {
    public static void main(String[] args) {
        HashSet<String> autos = new HashSet<String>();
        autos.add("Lada");
        autos.add("Toyota");
        autos.add("BMW");
        autos.add("Mersedes");
        for (String item : autos) {
            System.out.println(item);
        }
    }
}
