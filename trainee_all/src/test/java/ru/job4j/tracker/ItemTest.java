package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void whenSortIncrease() {
        Item first = new Item();
        first.setName("first");
        first.setId(1);
        Item second = new Item();
        second.setName("second");
        second.setId(2);
        List<Item> items = new ArrayList<>();
        items.add(second);
        items.add(first);
        for (Item item : items) {
            System.out.println(item);
        }
        Collections.sort(items, new ItemSortIncrease());
        for (Item item : items) {
            System.out.println(item);
        }
        assertThat(items.get(0), is(first));
    }

    @Test
    public void whenSortReverse() {
        Item first = new Item();
        first.setName("first");
        first.setId(1);

        Item second = new Item();
        second.setName("second");
        second.setId(2);

        Item third = new Item();
        third.setName("third");
        third.setId(3);

        List<Item> items = new ArrayList<>();
        items.add(second);
        items.add(first);
        items.add(third);

        Collections.sort(items, new ItemSortReverse());

        assertThat(items.get(0), is(third));
    }
}