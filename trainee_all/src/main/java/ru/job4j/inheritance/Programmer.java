package ru.job4j.inheritance;

public class Programmer extends Engineer {
    public Programmer(String name, String surname,
                      String education, String birthday, String category) {
        super(name, surname, education, birthday, category);
    }

    public void code(Drawing drawing) {

    }
}
