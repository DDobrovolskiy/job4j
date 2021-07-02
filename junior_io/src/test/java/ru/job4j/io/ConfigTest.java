package ru.job4j.io;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Dmitriy Dobrovolskiy"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Dmitriy Dobrovolskiy"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithCommentAndVoid() {
        String path = "./data/pair_with_and_void_comment.properties";
        Config config = new Config(path);
        config.load();
    }
}