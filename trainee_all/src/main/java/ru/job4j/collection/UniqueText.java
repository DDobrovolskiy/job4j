package ru.job4j.collection;

import java.util.Arrays;
import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String item : origin) {
            check.add(item);
        }
        for (String item : text) {
            if (!check.contains(item)) {
                return false;
            }
        }
        return true;
    }
}
