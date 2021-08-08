package ru.job4j.inheritance;

public class Dentist extends Doctor {

    public Dentist(String name, String surname, String education, String birthday, String degree) {
        super(name, surname, education, birthday, degree);
    }

    public void treat(Teeth teeth) {
    }
}
