package ru.job4j.inheritance;

public class Builder extends Engineer {

    public Builder(String name, String surname,
                   String education, String birthday, String category) {
        super(name, surname, education, birthday, category);
    }

    public void house(Drawing drawing) {

    }
}
