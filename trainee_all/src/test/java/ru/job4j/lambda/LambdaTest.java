package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LambdaTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = Lambda.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLinearFunctionThenKvadratResults() {
        List<Double> result = Lambda.diapason(0, 3, x -> x * x);
        List<Double> expected = Arrays.asList(0D, 1D, 4D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLinearFunctionThenPokazatelResults() {
        List<Double> result = Lambda.diapason(0, 3, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(1D, 2D, 4D);
        assertThat(result, is(expected));
    }
}