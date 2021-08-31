package ru.job4j.store;

import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;

import java.util.LinkedList;
import java.util.List;

public class LoadDetails {
    public static void main(String[] args) {
        Store store = DataAccessObject.instOf();

        List<Body> bodies = new LinkedList<>();
        bodies.add(new Body("Седан"));
        bodies.add(new Body("Хэтчбэк"));
        bodies.add(new Body("Универсал"));
        bodies.add(new Body("Лифтбэк"));
        bodies.add(new Body("Минивэн"));
        bodies.forEach(store::addBody);

        System.out.println(store.getBodies());

        List<Mark> marks = new LinkedList<>();
        marks.add(new Mark("Lada"));
        marks.add(new Mark("KIA"));
        marks.add(new Mark("Toyota"));
        marks.add(new Mark("Audi"));
        marks.add(new Mark("BMW"));
        marks.add(new Mark("Geely"));
        marks.add(new Mark("Honda"));
        marks.forEach(store::addMark);

        System.out.println(store.getMarks());
    }
}
