package ru.job4j.io.ex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Find {

    private static Predicate<String>
    predicate(String mask, String name) throws IllegalAccessException {
        if (mask.equals("mask")) {
            String reName = name.replaceAll("\\*", "\\.*");
            Pattern pattern = Pattern.compile(reName);
            return pattern.asPredicate();
        } else if (mask.equals("name")) {
            return s -> s.equals(name);
        } else if (mask.equals("regex")) {
            return Pattern.compile(name).asPredicate();
        }
        throw new IllegalAccessException("Выбранная маска не является mask/name/regex");
    }

    private static void write(List<Path> paths, String outFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(outFile))) {
            for (Path path : paths) {
                System.out.println(path.normalize());
                out.println(path.normalize().toString());
            }
        }
    }

    public static void main(String[] args) {
        Arguments arguments = Arguments.of(args);
        try {
            List<Path> paths = CoreSearch.search(new File(arguments.get("d")).toPath(),
                    predicate(arguments.get("t"), arguments.get("n")));
            write(paths, arguments.get("o"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
