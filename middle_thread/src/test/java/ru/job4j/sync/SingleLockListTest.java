package ru.job4j.sync;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        List<Integer> base = new LinkedList<>();
        SingleLockList<Integer> list = new SingleLockList<>(base);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();

        base.add(3);

        Set<Integer> rsl = new TreeSet<>();

        list.iterator().forEachRemaining(rsl::add);

        assertThat(rsl, is(Set.of(1, 2)));
    }
}