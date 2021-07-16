package ru.job4j.nonblock;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.AccessibleObject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenUpdateNormal() {
        Base base1 = new Base(1, 1);
        base1.setName("Groovy");
        Base base2 = new Base(1, 1);
        base2.setName("Victory");

        Cache cache = new Cache();

        Assert.assertTrue(cache.add(base1));
        Assert.assertTrue(cache.update(base2));
        System.out.println(cache.get(1));
        Assert.assertThat(2, is(cache.get(1).getVersion()));
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateFails() {
        Base base1 = new Base(1, 1);
        base1.setName("Groovy");
        Base base2 = new Base(1, 2);
        base2.setName("Victory");

        Cache cache = new Cache();

        cache.add(base1);
        cache.update(base2);
    }

    @Test
    public void whenUpdateWrong() {
        Base base1 = new Base(1, 1);
        base1.setName("Groovy");
        Base base2 = new Base(2, 1);
        base2.setName("Victory");

        Cache cache = new Cache();

        Assert.assertTrue(cache.add(base1));
        Assert.assertFalse(cache.update(base2));
        System.out.println(cache.get(1));
    }

    @Test
    public void whenDeleteNormal() {
        Base base1 = new Base(1, 1);
        base1.setName("Groovy");
        Base base2 = new Base(2, 1);
        base2.setName("Victory");

        Cache cache = new Cache();

        Assert.assertTrue(cache.add(base1));
        Assert.assertTrue(cache.add(base2));
        Assert.assertTrue(cache.delete(base1));
        Assert.assertNull(cache.get(1));
    }

    @Test
    public void whenDeleteFail() {
        Base base1 = new Base(1, 1);
        base1.setName("Groovy");
        Base base2 = new Base(2, 1);
        base2.setName("Victory");

        Cache cache = new Cache();

        Assert.assertTrue(cache.add(base1));
        Assert.assertTrue(cache.add(base2));
        Assert.assertTrue(cache.delete(base1));
        Assert.assertFalse(cache.delete(base1));
        Assert.assertNull(cache.get(1));
    }
}