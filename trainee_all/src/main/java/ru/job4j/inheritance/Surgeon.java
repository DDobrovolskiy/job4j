package ru.job4j.inheritance;

public class Surgeon extends Doctor {

    public Surgeon(String name, String surname, String education, String birthday, String degree) {
        super(name, surname, education, birthday, degree);
    }

    public void cut(Organ organ) {

    }
}
