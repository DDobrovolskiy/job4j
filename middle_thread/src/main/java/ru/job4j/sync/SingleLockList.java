package ru.job4j.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.*;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = list;
    }

    public void add(T element) {
        synchronized (this) {
            list.add(element);
        }
    }

    public synchronized T get(int index) {
        return copy(list).get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized  List<T> copy(List<T> list) {
        return List.copyOf(list);
    }
}