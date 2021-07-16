package ru.job4j.nonblock;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int i;
        int iNext;
        do {
            i = count.get();
            iNext = i + 1;
        } while (!count.compareAndSet(i, iNext));
    }

    public int get() {
        return count.get();
    }
}
