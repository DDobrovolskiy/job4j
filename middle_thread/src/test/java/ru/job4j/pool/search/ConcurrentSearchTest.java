package ru.job4j.pool.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConcurrentSearchTest {

    @Test
    public void search() {
        Integer[] array = new Integer[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        Assert.assertThat(4, is(ConcurrentSearch.search(array, 4)));
        Assert.assertThat(-1, is(ConcurrentSearch.search(array, 50)));
    }
}