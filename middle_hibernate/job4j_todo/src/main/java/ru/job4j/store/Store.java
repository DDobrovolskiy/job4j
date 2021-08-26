package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.Collection;

public interface Store {
    Collection<Item> getAllItem();

    Collection<Item> getOnlyDidntDoneItem();

    void save(Item item);

    boolean update(Item item);

    boolean updateDone(int id, boolean done);

    boolean delete(int id);
}
