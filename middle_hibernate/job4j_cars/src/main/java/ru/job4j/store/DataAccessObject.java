package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.announcements.Car;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DataAccessObject implements Store, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(DataAccessObject.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public List<Car> getCarsToday() {
        return this.tx(session -> session
                .createQuery(
                        "FROM Car c "
                        + "JOIN FETCH c.mark "
                        + "JOIN FETCH c.body "
                        + "JOIN FETCH c.user "
                        + "WHERE c.create "
                        + "BETWEEN :dataStart AND :dataNow")
                .setParameter("dataStart", Date.from(Instant.now().minus(1, ChronoUnit.DAYS)))
                .setParameter("dataNow", Date.from(Instant.now()))
                .list());
    }

    @Override
    public List<Car> getCarsWithPhoto() {
        return this.tx(session -> session
                .createQuery(
                        "FROM Car c "
                        + "JOIN FETCH c.mark "
                        + "JOIN FETCH c.body "
                        + "JOIN FETCH c.user "
                        + "WHERE c.photo IS NOT NULL")
                .list());
    }

    @Override
    public List<Car> getCarsOfMarkOnId(int id) {
        return this.tx(session -> session
                .createQuery(
                        "FROM Car c "
                        + "JOIN FETCH c.mark "
                        + "JOIN FETCH c.body "
                        + "JOIN FETCH c.user "
                        + "WHERE c.mark.id = :markId")
                .setParameter("markId", id)
                .list());
    }

    @Override
    public List<Car> getAllActiveCars() {
        return this.tx(session -> session
                .createQuery(
                        "FROM Car c "
                                + "JOIN FETCH c.mark "
                                + "JOIN FETCH c.body "
                                + "JOIN FETCH c.user "
                                + "WHERE c.status = :carStatus")
                .setParameter("carStatus", true)
                .list());
    }

    @Override
    public void clearCarsTable() {
        this.tx(session -> session.createQuery("DELETE FROM Car").executeUpdate());
    }

    @Override
    public void clearBodyTable() {
        this.tx(session -> {
            var bodies = getBodies();
            bodies.forEach(session::delete);
            return null;
        });
    }

    @Override
    public void clearMarkTable() {
        this.tx(session -> {
            var marks = getMarks();
            marks.forEach(session::delete);
            return null;
        });
    }

    @Override
    public void clearUserTable() {
        this.tx(session -> {
            var users = getAllUser();
            users.forEach(session::delete);
            return null;
        });
    }

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

    @Override
    public Optional<Body> getBodyOnId(int id) {
        return Optional.ofNullable((Body) this.tx(session ->
                session.createQuery("FROM Body b WHERE b.id = :idB")
                        .setParameter("idB", id)
                        .uniqueResult()));
    }

    @Override
    public Optional<Mark> getMarkOnId(int id) {
        return Optional.ofNullable((Mark) this.tx(session ->
                session.createQuery("FROM Mark m WHERE m.id = :idM")
                    .setParameter("idM", id)
                    .uniqueResult()));
    }

    @Override
    public void addUser(User user) {
        this.tx((session -> session.save(user)));
    }

    @Override
    public List<User> getAllUser() {
        return this.tx(session ->
                session.createQuery("from User", User.class).list());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable((User) this.tx(session ->
                session.createQuery("FROM User u WHERE u.email = :emailU")
                        .setParameter("emailU", email)
                        .uniqueResult()));
    }

    @Override
    public void addCar(Car car) {
        this.tx((session -> session.save(car)));
    }

    @Override
    public List<Car> getCarsByIdUser(int id) {
        return this.tx(session -> session
                .createQuery(
                        "FROM Car c "
                                + "JOIN FETCH c.mark m "
                                + "JOIN FETCH c.body b "
                                + "JOIN FETCH c.user u "
                                + "WHERE u.id = :idUser")
                .setParameter("idUser", id)
                .list());
    }

    @Override
    public void insertPhotoInCar(int idCar, String namePhoto) {
        this.tx(session -> session.createQuery(
                "UPDATE Car c "
                        + "SET c.photo = :namePhoto "
                        + "WHERE c.id = :idCar")
        .setParameter("namePhoto", namePhoto)
        .setParameter("idCar", idCar)
        .executeUpdate());
    }

    @Override
    public void changeStatusCar(int idCar, boolean statusCar) {
        this.tx(session -> session.createQuery(
                "UPDATE Car c "
                        + "SET c.status = :statusCar "
                        + "WHERE c.id = :idCar")
                .setParameter("statusCar", statusCar)
                .setParameter("idCar", idCar)
                .executeUpdate());
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
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
