package ru.job4j.store;

import ru.job4j.models.announcements.Car;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;

import java.util.List;
import java.util.Optional;

public interface Store {
    void addBody(Body body);

    List<Body> getBodies();

    void addMark(Mark mark);

    List<Mark> getMarks();

    Optional<Body> getBodyOnId(int id);

    Optional<Mark> getMarkOnId(int id);

    void addUser(User user);

    void addCar(Car car);

    List<Car> getCarsToday();

    List<Car> getCarsWithPhoto();

    List<Car> getCarsOfMarkOnId(int id);
}
