package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private int modCount = 0;
    private int size = 0;
    private Object[] container;

    public SimpleArray() {
        container = new Object[10];
    }

    public SimpleArray(int capacity) {
        container = new Object[capacity];
    }

    public T get(int index) {
        return (T) container[checkIndex(index)];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return container.length;
    }

    public void add(T model) {
        modCount++;
        if (size >= container.length) {
            int capacityNew = container.length * 2;
            Object[] containerNew = new Object[capacityNew];
            System.arraycopy(container, 0, containerNew, 0, size);
            container = containerNew;
        }
        container[size++] = model;
    }

    private int checkIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }
        return index;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int cursor = 0;

            private void checkModCount() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkModCount();
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[cursor++];
            }
        };
    }
}
