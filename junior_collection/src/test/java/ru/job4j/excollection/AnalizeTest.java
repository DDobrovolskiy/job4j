package ru.job4j.excollection;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenAddUser() {
        Analize analize = new Analize();
        List<Analize.User> first = List.of(
                new Analize.User(0, "Tom"),
                new Analize.User(1, "Dane"),
                new Analize.User(2, "Bill"));
        List<Analize.User> second = List.of(
                new Analize.User(0, "Tom"),
                new Analize.User(1, "Dane"),
                new Analize.User(2, "Bill"),
                new Analize.User(3, "Freeman"));

        Assert.assertThat(analize.diff(first, second).getAdded(), is(1));
    }

    @Test
    public void whenChangeUser() {
        Analize analize = new Analize();
        List<Analize.User> first = List.of(
                new Analize.User(0, "Tom"),
                new Analize.User(1, "Dane"),
                new Analize.User(2, "Bill"));
        List<Analize.User> second = List.of(
                new Analize.User(0, "Tom"),
                new Analize.User(1, "Dane"),
                new Analize.User(2, "Nike"));

        Assert.assertThat(analize.diff(first, second).getChanged(), is(1));
    }

    @Test
    public void whenDeleteUser() {
        Analize analize = new Analize();
        List<Analize.User> first = List.of(
                new Analize.User(0, "Tom"),
                new Analize.User(1, "Dane"),
                new Analize.User(2, "Bill"),
                new Analize.User(3, "Freeman"));
        List<Analize.User> second = List.of(
                new Analize.User(0, "Tom"),
                new Analize.User(1, "Dane"),
                new Analize.User(2, "Bill"));

        Assert.assertThat(analize.diff(first, second).getDeleted(), is(1));
    }

    @Test
    public void whenDifferentUser() {
        Analize analize = new Analize();
        List<Analize.User> first = List.of(
                new Analize.User(0, "Tom"),
                new Analize.User(1, "Dane"),
                new Analize.User(2, "Bill"));
        List<Analize.User> second = List.of(
                new Analize.User(0, "Nike"),
                new Analize.User(1, "Dane"),
                new Analize.User(3, "Freeman"));

        Assert.assertThat(analize.diff(first, second).getAdded(), is(1));
        Assert.assertThat(analize.diff(first, second).getChanged(), is(1));
        Assert.assertThat(analize.diff(first, second).getDeleted(), is(1));
    }
}