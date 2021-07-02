package ru.job4j.collection;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.lang.Object;
import java.util.Objects;

import static java.lang.Object.*;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
         if (contains(value)) {
             return false;
         }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T item : set) {
            if (Objects.equals(item, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
