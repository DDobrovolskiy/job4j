package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class HbmRun {
    private static final Logger LOG = LoggerFactory.getLogger(HbmRun.class.getName());

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        LOG.debug("Test");
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Car car = Car.of("Toyota", new Timestamp(1459510232000L), "owner");
            session.save(car);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
