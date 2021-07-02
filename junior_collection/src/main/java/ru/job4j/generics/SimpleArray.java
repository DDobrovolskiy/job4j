package ru.job4j.generics;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int length = 0;

    public SimpleArray(int capacity) {
        this.array = new Object[capacity];
    }

    public int getSize() {
        return length;
    }

    public boolean add(T model) {
        try {
            array[checkIndex(length++)] = model;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void set(int index, T model) {
        array[checkIndex(index)] = model;
    }

    public void remove(int index) {
        if (!(checkIndex(index) == length - 1)) {
            System.arraycopy(array,
                    checkIndex(index) + 1,
                    array,
                    checkIndex(index),
                    length - checkIndex(index) - 1);
        }
        length--;
    }

    public T get(int index) {
        return (T) array[checkIndex(index)];
    }

    private int checkIndex(int index) {
        if ((index < 0) || (index >= length)) {
            throw new IndexOutOfBoundsException("Индекс находится за пределами массива!");
        }
        return index;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < length;
            }

            @Override
            public T next() {
                return (T) array[cursor++];
            }
        };
    }
}
