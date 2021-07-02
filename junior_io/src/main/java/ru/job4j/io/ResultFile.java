package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static String getTable() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                stringBuilder.append(i + " x " + j + " = " + i * j + ";");
                stringBuilder.append(System.lineSeparator());
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(getTable().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
