package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EasyStream {

    private List<Integer> list;

    private EasyStream(List<Integer> list) {
        this.list = new ArrayList<>(list);
    }

    public static EasyStream of(List<Integer> list) {
        return new EasyStream(list);
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, fun.apply(list.get(i)));
        }
        return this;
    }

    public EasyStream filter(Predicate<Integer> fun) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!fun.test(iterator.next())) {
                iterator.remove();
            }
        }
        return this;
    }

    public List<Integer> collect() {
        return List.copyOf(list);
    }
}
