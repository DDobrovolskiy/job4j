package ru.job4j.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ListInMapTest {

    @Test
    public void convertToMap() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(100, "Globin"));
        students.add(new Student(10, "Bobin"));
        students.add(new Student(10, "Bobin"));
        students.add(new Student(100, "Globin"));
        Map<String, Student> actual = ListInMap.convertToMap(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Bobin", new Student(10, "Bobin"));
        expected.put("Globin", new Student(100, "Globin"));
        Assert.assertThat(actual, is(expected));
    }
}