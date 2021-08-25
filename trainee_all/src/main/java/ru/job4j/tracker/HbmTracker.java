package ru.job4j.tracker;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbmTracker implements Store, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(HbmTracker.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        var session = sf.openSession();
        session.beginTransaction();
        int id = (Integer) session.save(item);
        LOG.debug("Create items with id: {}", id);
        session.getTransaction().commit();
        session.close();
        item.setId(id);
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        try(var session = sf.openSession()) {
            session.beginTransaction();
            item.setId(id);
            session.update(item);
            session.getTransaction().commit();
            session.close();
            LOG.debug("Replace item with id: {}", id);
            return true;
        } catch (Exception e) {
            LOG.error("Don`t replace item, maybe invalid id", e);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try(var session = sf.openSession()) {
            session.beginTransaction();
            session.delete(new Item(id, null));
            session.getTransaction().commit();
            session.close();
            LOG.debug("Delete item with id: {}", id);
            return true;
        } catch (Exception e) {
            LOG.error("Don`t delete item, maybe invalid id", e);
            return false;
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.tracker.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ru.job4j.tracker.Item WHERE name = :itemsName");
        query.setParameter("itemsName", key);
        List result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
