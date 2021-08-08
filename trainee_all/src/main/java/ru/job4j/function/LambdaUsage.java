package ru.job4j.function;

import java.util.Comparator;

public class LambdaUsage {
    public static void main(String[] args) {
        Comparator<String> cmpDescSize = (left, right) -> {
            System.out.println("compare - "
                    + Integer.parseInt(right)
                    + " : "
                    + Integer.parseInt(left));
            return Integer.compare(right.length(), left.length());
        };
    }
}
