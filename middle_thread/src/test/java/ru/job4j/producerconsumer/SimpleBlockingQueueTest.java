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

    @Test
    public void whenNormalWork() throws InterruptedException {
        List<Integer> listDesc = new LinkedList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        List<Integer> listSource = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

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
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Загрузка заверщена");
        });

        Thread consumer = new Thread(() -> {
            int i = 0;
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("Текущий i: " + i++);
                    listDesc.add(queue.poll());
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Consumer END");
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();

        Assert.assertThat(listSource, is(listDesc));
    }
}