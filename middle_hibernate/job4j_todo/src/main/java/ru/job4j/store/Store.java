package ru.job4j.store;

import ru.job4j.models.Item;
import ru.job4j.models.User;

import java.util.Collection;
import java.util.Optional;

public interface Store {
    Collection<Item> getAllItem();

    Collection<Item> getOnlyDidntDoneItem();

    void save(Item item);

    boolean update(Item item);

    boolean updateDone(int id, boolean done);

    boolean delete(int id);

    Optional<User> findUserByEmail(String email);

    void addUser(User user);
}
