package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Item;

import java.util.Collection;

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
        Collection result = null;
        LOG.debug("Start session - getAllItem");
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.model.Item").list();
            LOG.debug("Result: {}", result);
            session.getTransaction().commit();
            session.close();
        }
        LOG.debug("Return result");
        return result;
    }

    @Override
    public Collection<Item> getOnlyDidntDoneItem() {
        Collection result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from ru.job4j.model.Item WHERE done = :itemsDone");
            query.setParameter("itemsDone", false);
            result = query.list();
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }

    @Override
    public void save(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();
            LOG.debug("Add item in sql: {}", item);
        }
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
        LOG.debug("UpdateDone");
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(
                    "UPDATE ru.job4j.model.Item SET done = :itemsDone WHERE id = :itemsId");
            query.setParameter("itemsId", id);
            query.setParameter("itemsDone", done);
            int i = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            LOG.debug("UpdateDone - close");
            return i == 1;
        } catch (Exception e) {
            LOG.error("Error update done", e);
        }
        return false;
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

    @Override
    public void close() throws Exception {
        if (sf != null) {
            sf.close();
        }
    }
}
