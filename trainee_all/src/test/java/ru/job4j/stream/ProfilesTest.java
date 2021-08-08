package ru.job4j.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenCollectNormal() {
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile(new Address(
                "City1", "Street1", 1, 1)));
        profilesList.add(new Profile(new Address(
                "City2", "Street2", 10, 10)));
        List<Address> actual = new Profiles().collect(profilesList);
        System.out.println(actual);
        List<Address> expected = new ArrayList<>();
        expected.add(new Address(
                "City1", "Street1", 1, 1));
        expected.add(new Address(
                "City2", "Street2", 10, 10));
        System.out.println(expected);
        Assert.assertThat(actual, is(expected));
    }

    @Test
    public void whenCollectNull() {
        List<Profile> profilesList = new ArrayList<>();
        List<Address> actual = new Profiles().collect(profilesList);
        System.out.println(actual);
        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void whenCollectHasDuplicat() {
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile(new Address(
                "City2", "Street2", 10, 10)));
        profilesList.add(new Profile(new Address(
                "City1", "Street1", 1, 1)));
        profilesList.add(new Profile(new Address(
                "City2", "Street2", 10, 10)));
        List<Address> actual = new Profiles().collect(profilesList);
        System.out.println(actual);
        List<Address> expected = new ArrayList<>();
        expected.add(new Address(
                "City1", "Street1", 1, 1));
        expected.add(new Address(
                "City2", "Street2", 10, 10));
        System.out.println(expected);
        Assert.assertThat(actual, is(expected));
    }
}