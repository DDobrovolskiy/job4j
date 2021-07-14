package ru.job4j.sync;

import net.jcip.annotations.ThreadSafe;
import java.io.*;
import java.util.function.Predicate;

@ThreadSafe
public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (var i = new BufferedReader(new FileReader(file))) {
            i.lines().forEach(s -> {
                for (int chars = 0; chars < s.length(); chars++) {
                    if (filter.test(s.charAt(chars))) {
                        output.append(s.charAt(chars));
                    }
                }
            });
            return output.toString();
        }
    }
}
