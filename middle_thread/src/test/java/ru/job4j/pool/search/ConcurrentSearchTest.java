package ru.job4j.pool.search;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConcurrentSearchTest {

    @Test
    public void search() {
        int[] array1 = new int[] {5, 6, 8, 1, 2, 7, 3, 9, 4};
        int[] array2 = new int[] {5, 6, 8, 1, 2, 7, 3, 9, 4, 10, 11, 12, 13, 14, 15, 20};
        Assert.assertThat(8, is(ConcurrentSearch.search(array1, 4)));
        Assert.assertThat(8, is(ConcurrentSearch.search(array2, 4)));
        Assert.assertThat(-1, is(ConcurrentSearch.search(array1, 50)));
        Assert.assertThat(-1, is(ConcurrentSearch.search(array2, 50)));
    }
}