package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void maxAndMinIsEmpty() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of();
        Assert.assertTrue(maxMin.max(list, Comparator.naturalOrder()).isEmpty());
        Assert.assertTrue(maxMin.min(list, Comparator.naturalOrder()).isEmpty());
    }

    @Test
    public void maxAndMinOne() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1);
        Assert.assertEquals(maxMin.max(list, Comparator.naturalOrder()).get().intValue(), 1);
        Assert.assertEquals(maxMin.min(list, Comparator.naturalOrder()).get().intValue(), 1);
    }

    @Test
    public void maxAndMinNormal() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(0, -3, -2, -1, 1, 2, 3);
        Assert.assertEquals(maxMin.max(list, Comparator.naturalOrder()).get().intValue(), 3);
        Assert.assertEquals(maxMin.min(list, Comparator.naturalOrder()).get().intValue(), -3);
    }
}