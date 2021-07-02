package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите директорию поиска:");
        String directory = scanner.next();
        Files.walkFileTree(Path.of(directory), new DuplicatesVisitor());
    }
}
