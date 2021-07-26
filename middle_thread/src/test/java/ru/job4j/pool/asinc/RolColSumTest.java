package ru.job4j.pool.asinc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static ru.job4j.pool.asinc.RolColSum.*;

@State(Scope.Benchmark)
public class RolColSumTest {
    private static Sums[] actual;
    private static int[][] in;

    //Сначала инициализируются поля (считается 1 раз на все тесты)
    @BeforeClass
    @Setup(Level.Trial)
    public static void init() {
        int length = 10_000;
        actual = new Sums[length];
        in = new int[length][length];
        for (int i = 0; i < in.length; i++) {
            actual[i] = new Sums(length * length, length * length);
            for (int j = 0; j < in[0].length; j++) {
                in[i][j] = length;
            }
        }
        System.out.println("Create matrix");
    }

    @Test
    public void whenSumNotAsinc() {
        Assert.assertThat(actual, is(sum(in)));
    }

    @Test
    public void whenSumAsinc() throws InterruptedException, ExecutionException {
        Assert.assertThat(actual, is(asyncSum(in)));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1) // число итераций для прогрева нашей функции
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 1)
    public Sums[] jmhNotAsincSum() throws InterruptedException {
        return sum(in);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1) // число итераций для прогрева нашей функции
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 1)
    public Sums[] jmhAsincSum() throws InterruptedException, ExecutionException {
        return asyncSum(in);
    }

    // Benchmark                     Mode  Cnt     Score     Error  Units
    // RolColSumTest.jmhAsincSum     avgt    5   645,290 ±  71,516  ms/op
    // RolColSumTest.jmhNotAsincSum  avgt    5  2335,209 ± 130,163  ms/op
}