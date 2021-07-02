package ru.job4j.collection.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleMyMapTest {

    @Test
    public void insertAndGetValue() {
        SimpleMyMap<Integer, Integer> simpleMyMap = new SimpleMyMap<>();
        Assert.assertTrue(simpleMyMap.insert(0, 10));
        Assert.assertTrue(simpleMyMap.insert(1, 11));
        Assert.assertTrue(simpleMyMap.insert(2, 12));
        Assert.assertTrue(simpleMyMap.insert(3, 13));
        Assert.assertTrue(simpleMyMap.insert(4, 14));

        Assert.assertThat(simpleMyMap.get(0), is(10));
        Assert.assertThat(simpleMyMap.get(1), is(11));
        Assert.assertThat(simpleMyMap.get(2), is(12));
        Assert.assertThat(simpleMyMap.get(3), is(13));
        Assert.assertThat(simpleMyMap.get(4), is(14));

        Assert.assertFalse(simpleMyMap.insert(18, 24));
        Assert.assertThat(simpleMyMap.get(2), is(12));
    }

    @Test
    public void whenDeleteValue() {
        SimpleMyMap<Integer, Integer> simpleMyMap = new SimpleMyMap<>();
        simpleMyMap.insert(0, 10);
        simpleMyMap.insert(1, 11);
        simpleMyMap.insert(2, 12);

        Assert.assertTrue(simpleMyMap.insert(1, 21));
        Assert.assertThat(simpleMyMap.get(1), is(21));

        Assert.assertTrue(simpleMyMap.delete(1));
        Assert.assertFalse(simpleMyMap.delete(1));

        Assert.assertTrue(simpleMyMap.insert(17, 17));
        Assert.assertThat(simpleMyMap.get(17), is(17));

        Assert.assertFalse(simpleMyMap.delete(1));
        Assert.assertTrue(simpleMyMap.delete(17));
    }

    @Test
    public void whenGetIterator() {
        SimpleMyMap<Integer, Integer> simpleMyMap = new SimpleMyMap<>();
        simpleMyMap.insert(0, 10);
        simpleMyMap.insert(1, 11);
        simpleMyMap.insert(2, 12);

        var i = simpleMyMap.iterator();
        Assert.assertTrue(i.hasNext());
        Assert.assertThat(i.next().getValue(), is(10));
        Assert.assertTrue(i.hasNext());
        Assert.assertThat(i.next().getValue(), is(11));
        Assert.assertTrue(i.hasNext());
        Assert.assertThat(i.next().getValue(), is(12));
        Assert.assertFalse(i.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorException() {
        SimpleMyMap<Integer, Integer> simpleMyMap = new SimpleMyMap<>();

        var i = simpleMyMap.iterator();
        i.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorChange() {
        SimpleMyMap<Integer, Integer> simpleMyMap = new SimpleMyMap<>();
        simpleMyMap.insert(0, 10);
        var i = simpleMyMap.iterator();
        simpleMyMap.insert(2, 10);
        i.next();
    }

    @Test
    public void whenChangeCapacityAndChangeIndex() {
        SimpleMyMap<Integer, Integer> simpleMyMap = new SimpleMyMap<>();
        Assert.assertTrue(simpleMyMap.insert(118, 118));
        Assert.assertThat(simpleMyMap.get(118), is(118));
        for (int i = 0; i < 16; i++) {
            simpleMyMap.insert(i, i);
        }
        Assert.assertThat(simpleMyMap.get(118), is(118));
    }

    @Test
    public void whenKeyIsNull() {
        SimpleMyMap<Integer, Integer> simpleMyMap = new SimpleMyMap<>();
        Assert.assertTrue(simpleMyMap.insert(null, null));
        Assert.assertNull(simpleMyMap.get(null));
    }
}