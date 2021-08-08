package ru.job4j.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MatrixInListTest {

    @Test
    public void convertMatrixInList() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> actual = MatrixInList.convertMatrixInList(arr);
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertThat(actual, is(expected));
    }
}