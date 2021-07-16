package ru.job4j.pool;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        createPool(Runtime.getRuntime().availableProcessors());
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public synchronized void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private void createPool(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Dead pool");
        }
        for (int i = 0; i < capacity; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted() || !tasks.isEmpty()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            System.out.println(thread.getState());
            thread.start();
            threads.add(thread);
            System.out.println("Thread add: " + i);
        }
    }
}

