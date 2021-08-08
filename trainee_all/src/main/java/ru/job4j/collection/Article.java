package ru.job4j.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Article {
    public static boolean generateBy(String origin, String line) {
        Set<String> originSet = new HashSet<>(
                Arrays.asList(origin
                        .toLowerCase(Locale.ROOT)
                        .replaceAll("\\p{Punct}", "")
                        .split(" ")));
        Set<String> lineSet = new HashSet<>(
                Arrays.asList(line
                        .toLowerCase(Locale.ROOT)
                        .replaceAll("\\p{Punct}", "")
                        .split(" ")));
        return originSet.containsAll(lineSet);
    }
}
