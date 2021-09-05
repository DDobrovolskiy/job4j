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

    List<User> getAllUser();

    Optional<User> getUserByEmail(String email);

    void addCar(Car car);

    List<Car> getCarsToday();

    List<Car> getCarsWithPhoto();

    List<Car> getCarsOfMarkOnId(int id);

    List<Car> getAllActiveCars();

    void clearCarsTable();

    void clearBodyTable();

    void clearMarkTable();

    void clearUserTable();

    List<Car> getCarsByIdUser(int id);

    void insertPhotoInCar(int idCar, String namePhoto);

    void changeStatusCar(int idCar, boolean statusCar);
}
