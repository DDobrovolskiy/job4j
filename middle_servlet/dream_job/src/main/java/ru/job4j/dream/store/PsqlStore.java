package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = PropertyFactory.load("db.properties");
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            LOG.error("Ошибка загрузки драйвера базы данных: ", e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT id, name, data FROM posts")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getTimestamp("data").toLocalDateTime()));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT id, name, city_id, data FROM candidates ")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getInt("city_id"),
                            it.getTimestamp("data").toLocalDateTime()));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return candidates;
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            create(candidate);
        } else {
            update(candidate);
        }
    }

    @Override
    public Candidate findByIdCandidate(int id) {
        Candidate candidate = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement(
                             "SELECT id, name, city_id, data FROM candidates")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    LOG.warn("Не найдена запись канждидата с id = {}", id);
                } else {
                    candidate = new Candidate(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("city_id"),
                            resultSet.getTimestamp("data").toLocalDateTime());
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return candidate;
    }

    @Override
    public void deleteCandidate(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement("DELETE FROM candidates WHERE id = ?")
        ) {
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            LOG.error("Ошибка удаления данных в SQL: ", e);
        }
    }

    @Override
    public void save(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement(
                             "INSERT INTO users(name, email, password) VALUES (?, ?, ?)")
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Ошибка сохранения данных SQL: ", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement(
                             "SELECT id, name, email, password "
                                     + "FROM users "
                                     + "WHERE email = ?")) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    LOG.warn("Не найден User с email = {}", email);
                } else {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return user;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }

    private Post create(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement(
                             "INSERT INTO posts(name) VALUES (?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка создания данных SQL: ", e);
        }
        return post;
    }

    private Candidate create(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement(
                             "INSERT INTO candidates(name, city_id) VALUES (?, ?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getCityId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка создание данных SQL: ", e);
        }
        return candidate;
    }

    private void update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement("UPDATE posts SET name = ? WHERE id = ?")
        ) {
            ps.setString(1, post.getName());
            ps.setInt(2, post.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Ошибка обновления данных SQL: ", e);
        }
    }

    private void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement("UPDATE candidates SET name = ?, city_id = ? WHERE id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getCityId());
            ps.setInt(3, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Ошибка обновления данных SQL: ", e);
        }
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement(
                             "SELECT id, name, data "
                                     + "FROM posts "
                                     + "WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    LOG.warn("Не найдена запись поста с id = {}", id);
                } else {
                    post = new Post(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("data").toLocalDateTime());
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return post;
    }

    @Override
    public Collection<City> findAllCity() {
        List<City> cities = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT city_id, city_name FROM cities")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    cities.add(new City(
                            it.getInt("city_id"),
                            it.getString("city_name")));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return cities;
    }

    public City findCityById(int id) {
        City city = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement(
                             "SELECT city_id, city_name "
                                     + "FROM cities "
                                     + "WHERE city_id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    LOG.warn("Не найдена запись поста с id = {}", id);
                } else {
                    city = new City(
                            resultSet.getInt("city_id"),
                            resultSet.getString("city_name"));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return city;
    }

    @Override
    public Collection<Candidate> findAllCandidatesNow() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT id, name, city_id, data FROM candidates "
                             + "WHERE data "
                             + "BETWEEN current_timestamp - interval '1 day' AND current_timestamp")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getInt("city_id"),
                            it.getTimestamp("data").toLocalDateTime()));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return candidates;
    }

    @Override
    public Collection<Post> findAllPostNow() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT id, name, data FROM posts "
                             + "WHERE data "
                             + "BETWEEN current_timestamp - interval '1 day' AND current_timestamp")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getTimestamp("data").toLocalDateTime()));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return posts;
    }
}
