package ru.job4j.producerconsumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<Integer> queue;
    private List<Integer> listSource;
    private List<Integer> listDesc;

    @Before
    public void begin() {
        listDesc = new LinkedList<>();
        queue = new SimpleBlockingQueue<>(3);
        listSource = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    public void whenNormalWork() throws InterruptedException {
        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i : listSource) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Загрузка заверщена");
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < listSource.size(); i++) {
                try {
                    listDesc.add(queue.poll());
                    System.out.println("Текущий i: " + i);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        Assert.assertThat(listSource, is(listDesc));
    }
}