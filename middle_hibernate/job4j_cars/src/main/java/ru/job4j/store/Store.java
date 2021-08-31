package ru.job4j.store;

import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;

import java.util.List;

public interface Store {
    void addBody(Body body);

    List<Body> getBodies();

    void addMark(Mark mark);

    List<Mark> getMarks();
}
