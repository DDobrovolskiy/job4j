package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Animal {
    @XmlAttribute
    private String name = "";

    public Animal() {

    }

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
