package ru.job4j.token.file;

import net.jcip.annotations.Immutable;

@Immutable
public class TokenDefault implements Token {
    private final String name;
    private final String filename;
    private final String fileContentType;

    public TokenDefault(String name, String filename, String fileContentType) {
        this.name = name;
        this.filename = filename;
        this.fileContentType = fileContentType;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String filename() {
        return filename;
    }

    @Override
    public String fileContentType() {
        return fileContentType;
    }
}
