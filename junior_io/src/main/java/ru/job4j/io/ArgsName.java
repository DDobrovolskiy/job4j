package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException();
        }
        return rsl;
    }

    private void parse(String[] args) {
        Arrays.asList(args).forEach(arg -> {
            if (arg.startsWith("-")) {
                String[] param = arg.split("=");
                if (param.length != 2) {
                   throw new IllegalArgumentException();
                }
                values.put(param[0].substring(1), param[1]);
            }
        });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
