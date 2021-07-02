package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {

    private final int[] data;
    private int cursorNext = 0;

    public EvenIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (cursorNext < data.length && (data[cursorNext] % 2 == 1)) {
            cursorNext++;
        }
        return cursorNext < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[cursorNext++];
    }
}
