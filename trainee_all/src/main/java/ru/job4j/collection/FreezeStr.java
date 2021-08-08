package ru.job4j.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        Map<String, Integer> mapLeft = new HashMap<>();
        Arrays.asList(left.split("")).stream()
                .forEach(item -> {
                    mapLeft.computeIfPresent(item, (key, value) -> value + 1);
                    mapLeft.putIfAbsent(item, 1);
                });
        for (String key : Arrays.asList(right.split(""))) {
            if (mapLeft.containsKey(key)) {
                if (mapLeft.get(key) - 1 == 0) {
                    mapLeft.remove(key);
                } else {
                    mapLeft.put(key, mapLeft.get(key) - 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
