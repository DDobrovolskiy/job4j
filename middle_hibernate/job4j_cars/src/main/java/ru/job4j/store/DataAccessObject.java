package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;

import java.util.List;
import java.util.function.Function;

public class DataAccessObject implements Store, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(DataAccessObject.class.getName());
    private final SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new DataAccessObject();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public void addBody(Body body) {
        this.tx(session -> session.save(body));
    }

    @Override
    public List<Body> getBodies() {
        return this.tx(session ->
                session.createQuery("from Body", Body.class).list());
    }

    @Override
    public void addMark(Mark mark) {
        this.tx((session -> session.save(mark)));
    }

    @Override
    public List<Mark> getMarks() {
        return this.tx(session ->
                session.createQuery("from Mark", Mark.class).list());
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        if (sf != null) {
            sf.close();
        }
    }
}
