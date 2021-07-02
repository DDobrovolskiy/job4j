package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SimpleMap {
    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar();
        birthday.set(1990, 10, 10);

        User user1 = new User("Name", 1, birthday);
        User user2 = new User("Name", 1, birthday);

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
