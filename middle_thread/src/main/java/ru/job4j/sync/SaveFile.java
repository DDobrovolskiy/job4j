package ru.job4j.sync;

import net.jcip.annotations.ThreadSafe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@ThreadSafe
class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (var o = new BufferedWriter(new FileWriter(file))) {
            o.write(content);
        }
    }

}
