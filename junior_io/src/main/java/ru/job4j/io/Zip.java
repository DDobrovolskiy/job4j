package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            sources.forEach(file -> {
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.putNextEntry(new ZipEntry(file.normalize().toString()));
                    zip.write(out.readAllBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry("pom.xml"));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName param = ArgsName.of(args);
        if ((param.get("d") == null) || (param.get("e") == null) || (param.get("o") == null)) {
            throw new IllegalArgumentException("Argument d=/e=/o=?");
        }
        try {
            List<Path> paths = Search.search(new File(param.get("d")).toPath(), path ->
                    !path.toFile().getName().endsWith(param.get("e")));
            Zip.packFiles(paths, new File(param.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
