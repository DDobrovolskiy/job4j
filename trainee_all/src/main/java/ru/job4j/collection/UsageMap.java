package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("dda.90@mail.ru", "Dobrovolskiy DA");
        for (Map.Entry<String, String> item : hashMap.entrySet()) {
            System.out.println(item.getKey() + " = " + item.getValue());
        }
    }
}
