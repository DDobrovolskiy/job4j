package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Car;
import ru.job4j.models.Mark;

import java.util.LinkedList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {

        try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();

            List<Car> cars = new LinkedList<>();
            cars.add(Car.of("A1"));
            cars.add(Car.of("A3"));
            cars.add(Car.of("A4"));
            cars.add(Car.of("Q3"));
            cars.add(Car.of("Q7"));

            Mark audi = Mark.of("Audi");

            cars.forEach(audi::addCar);

            session.save(audi);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
