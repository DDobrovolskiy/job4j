package ru.job4j.builder;

import java.util.Comparator;

public class BulidUserB {
    public static void main(String[] args) {
        UserB userB = UserB.userB()
                .buildId(10)
                .buildCount(10)
                .buildName("null")
                .build(Comparator.naturalOrder())
                .buildNotify("no")
                .buildEnd("end")
                .build();
        System.out.println(userB);
    }
}
