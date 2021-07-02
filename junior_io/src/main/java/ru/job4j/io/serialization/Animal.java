package ru.job4j.io.serialization;

public class Animal {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + '}';
    }
}
