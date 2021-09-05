package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {
    private final Store store = new HbmTracker();

    @Test
    public void replace() {
        Item item = new Item("test");
        item = store.add(item);

        String newName = "name";
        store.replace(item.getId(), new Item(newName));

        Item result = store.findById(item.getId());

        Assert.assertThat(result.getId(), is(item.getId()));
        Assert.assertThat(result.getName(), is(newName));
    }

    @Test
    public void delete() {
        String name = "name";
        Item item = new Item(name);
        item = store.add(item);

        Item resultNotNull = store.findById(item.getId());

        Assert.assertThat(resultNotNull.getName(), is(name));

        store.delete(item.getId());

        Item resultNull = store.findById(item.getId());

        Assert.assertNull(resultNull);
    }

    @Test
    public void findAll() {
        String name = "name";
        Item item = new Item(name);
        store.add(item);

        var result = store.findAll();

        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0).getName(), is(name));
    }

    @Test
    public void findByName() {
        String name = "name";
        Item item = new Item(name);
        store.add(item);

        var result = store.findByName(name);

        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0).getName(), is(name));
    }
}