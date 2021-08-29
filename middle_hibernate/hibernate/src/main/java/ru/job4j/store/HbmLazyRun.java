package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Car;
import ru.job4j.models.Mark;

import java.util.LinkedList;
import java.util.List;

public class HbmLazyRun {
    public static void main(String[] args) {
        List<Mark> marks = new LinkedList<>();
        try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();

            marks = session
                    .createQuery("select distinct m from Mark m join fetch m.cars")
                    .list();

            //Mark audi = session.get(Mark.class, 1);
            //System.out.println(audi);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Result:");
        System.out.println(marks);

    }
}
