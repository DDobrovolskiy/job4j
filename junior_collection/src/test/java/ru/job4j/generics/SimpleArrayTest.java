package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenSimpleArrayCreated() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        Assert.assertEquals(0, simpleArray.getSize());
    }

    @Test
    public void whenAddFalse() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);
        simpleArray.add(10);
        Assert.assertFalse(simpleArray.add(10));
    }

    @Test
    public void whenAddTrue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        Assert.assertTrue(simpleArray.add(10));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetValueFail() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        int item = simpleArray.get(0);
    }

    @Test()
    public void whenGetValueTrue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(10);
        int actual = simpleArray.get(0);
        assertEquals(actual, 10);
    }

    @Test()
    public void whenSetValueTrue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.set(1, 10);
        int actual = simpleArray.get(1);
        assertEquals(actual, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetValueFalse() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.set(1, 10);
    }

    @Test()
    public void whenRemoveThatLengthChange() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(1);
        assertEquals(simpleArray.getSize(), 2);
    }

    @Test()
    public void whenRemoveValueTrue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(1);
        int actual = simpleArray.get(1);
        assertEquals(actual, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveValueFail() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(10);
    }

    @Test()
    public void whenIterator() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(4);
        simpleArray.remove(0);
        simpleArray.remove(4);
        List<Integer> actual = List.of(1, 2, 3, 4);
        List<Integer> expected = new ArrayList<>();
        for (int item : simpleArray) {
            expected.add(item);
        }
        assertThat(actual, is(expected));
    }
}