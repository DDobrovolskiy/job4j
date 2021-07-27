package ru.job4j.pool.search;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ConcurrentSearch extends RecursiveTask<Integer> {
    private final int[] array;
    private final int item;
    private final int from;
    private final int to;

    public ConcurrentSearch(int[] array, int item, int from, int to) {
        this.array = array;
        this.item = item;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (this.from - this.to < 10) {
            return searchInArray(array, item, 0, array.length - 1);
        }
        int mid = (from + to) / 2;

        var leftSearch = new ConcurrentSearch(array, item, from, mid);
        var rightSearch = new ConcurrentSearch(array, item, mid + 1, to);

        leftSearch.fork();
        rightSearch.fork();

        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public static int search(int[] array, int item) {
        return new ForkJoinPool().invoke(
                new ConcurrentSearch(array, item, 0, array.length - 1));
    }

    private static int searchInArray(int[] array, int item, int from, int to) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                result = i;
            }
        }
        return result;
    }

}
