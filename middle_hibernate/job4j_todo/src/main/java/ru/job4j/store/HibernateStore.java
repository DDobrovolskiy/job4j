package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Item;

import java.util.Collection;
import java.util.function.Function;

public class HibernateStore implements Store, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(HibernateStore.class.getName());
    private final SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new HibernateStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Item> getAllItem() {
        return this.tx(session -> session.createQuery("from ru.job4j.model.Item").list());
    }

    @Override
    public Collection<Item> getOnlyDidntDoneItem() {
        return  this.tx(session ->
            session.createQuery("from ru.job4j.model.Item WHERE done = :itemsDone")
                .setParameter("itemsDone", false)
                .list());
    }

    @Override
    public void save(Item item) {
        this.tx(session -> session.save(item));
    }

    @Override
    public boolean update(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            session.close();
            LOG.debug("Update item: {}", item);
            return true;
        } catch (Exception e) {
            LOG.error("Don`t update item", e);
            return false;
        }
    }

    @Override
    public boolean updateDone(int id, boolean done) {
        int i = this.tx(session ->
                session.createQuery(
                        "UPDATE ru.job4j.model.Item SET done = :itemsDone WHERE id = :itemsId")
        .setParameter("itemsDone", done)
        .setParameter("itemsId", id)
        .executeUpdate());
        return i == 1;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item item = new Item();
            item.setId(id);
            session.update(item);
            session.getTransaction().commit();
            session.close();
            LOG.debug("Delete success item id: {}", id);
            return true;
        } catch (Exception e) {
            LOG.error("Don`t delete item", e);
            return false;
        }
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
