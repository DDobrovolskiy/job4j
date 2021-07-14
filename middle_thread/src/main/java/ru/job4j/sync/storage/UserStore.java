package ru.job4j.sync.storage;

public interface UserStore {
    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    User find(int id);
}