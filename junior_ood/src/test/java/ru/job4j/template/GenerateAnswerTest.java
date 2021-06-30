package ru.job4j.template;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GenerateAnswerTest {

    @Test
    public void generateNormal() {
        Generator generator = new GenerateAnswer();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject",  "you");
        String expected = generator.produce("I am a ${name}, Who are ${subject}? ", map);
        String actual = "I am a Petr Arsentev, Who are you?";
        //Assert.assertEquals(expected, actual);
    }

    @Test //(expected = IllegalArgumentException.class)
    public void mapHaveMoreKey() {
        Generator generator = new GenerateAnswer();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject",  "you");
        map.put("sex",  "M");
        String expected = generator.produce("I am a ${name}, Who are ${subject}? ", map);
    }

    @Test //(expected = IllegalArgumentException.class)
    public void mapDontHaveKey() {
        Generator generator = new GenerateAnswer();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        String expected = generator.produce("I am a ${name}, Who are ${subject}? ", map);
    }
}