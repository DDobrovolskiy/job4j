package ru.job4j.io.serialization.json;

public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + '}';
    }
}
