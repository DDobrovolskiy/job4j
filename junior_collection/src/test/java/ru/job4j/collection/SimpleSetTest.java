package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenIteratorTrue() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator<Integer> i = set.iterator();
        assertTrue(i.hasNext());
        assertThat(i.next(), is(1));
        assertFalse(i.hasNext());
    }

    @Test
    public void whenAdd() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(null));
        assertTrue(set.add(2));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
        assertFalse(set.add(2));
    }
}