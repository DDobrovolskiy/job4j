package ru.job4j.io.ex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Arguments {
    private static Map<String, String> params = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        String rsl = params.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException();
        }
        return rsl;
    }

    public static Arguments of(String[] arg) {
        Arguments arguments = new Arguments();
        arguments.parsing(arg);
        return arguments;
    }

    private void parsing(String[] arg) {
        Arrays.asList(arg).forEach(s -> {
            if (s.startsWith("-")) {
                String[] values = s.split("=");
                if (values.length != 2) {
                    throw new IllegalArgumentException();
                }
                params.put(values[0].substring(1), values[1]);
            }
        });
    }
}
