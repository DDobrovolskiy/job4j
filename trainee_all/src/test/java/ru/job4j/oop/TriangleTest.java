package ru.job4j.oop;

import junit.framework.TestCase;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class TriangleTest extends TestCase {

    public void testArea() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 4);
        Point third = new Point(4, 0);
        Triangle triangle = new Triangle(first, second, third);
        double area = triangle.area();
        assertThat(area, closeTo(8, 0.001));
    }
}