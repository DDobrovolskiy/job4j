package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void workNormal() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        AtomicInteger x = new AtomicInteger();

        for (int i = 0; i < 50; i++) {
            System.out.println("runner add: " + i);
            threadPool.work(() -> {
                System.out.println(Thread.currentThread().getName()
                        + " x = "
                        + x.incrementAndGet());
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("Pool complite");

        Thread.sleep(5000);

        threadPool.shutdown();

        assertThat(x.get(), is(50));
    }
}