package ru.job4j.pool.search;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ConcurrentSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T item;
    private final int from;
    private final int to;

    public ConcurrentSearch(T[] array, T item, int from, int to) {
        this.array = array;
        this.item = item;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (this.from - this.to < 10) {
            return searchInArray();
        }
        int mid = (from + to) / 2;

        var leftSearch = new ConcurrentSearch<>(array, item, from, mid);
        var rightSearch = new ConcurrentSearch<>(array, item, mid + 1, to);

        leftSearch.fork();
        rightSearch.fork();

        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public static <T> int search(T[] array, T item) {
        return new ForkJoinPool().invoke(
                new ConcurrentSearch<>(array, item, 0, array.length - 1));
    }

    private int searchInArray() {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                result = i;
                break;
            }
        }
        return result;
    }

}
