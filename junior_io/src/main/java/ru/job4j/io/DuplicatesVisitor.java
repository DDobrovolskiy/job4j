package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(
                file.toFile().length(),
                file.getFileName().toString());
        if (!files.add(newFile)) {
            System.out.println("Duplicate detected!");
            System.out.println(file.getFileName());
            System.out.println(String.format("size : %s",
                    file.toFile().length() + " byte"));
        }
        return super.visitFile(file, attrs);
    }
}
