package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MaxMin {
    public <T> Optional<T> max(List<T> values, Comparator<T> comparator) {
        return getFor(values, comparator, v -> v < 0);
    }

    public <T> Optional<T> min(List<T> values, Comparator<T> comparator) {
        return getFor(values, comparator, v -> v > 0);
    }

    private <T> Optional<T> getFirst(List<T> values) {
        if (values.size() > 0) {
            return Optional.of(values.get(0));
        }
        return  Optional.empty();
    }

    private <T> Optional<T> getFor(List<T> values, Comparator<T> compare, Predicate<Integer> pred) {
        Optional<T> rsl = getFirst(values);
        if (rsl.isPresent()) {
            for (T value : values) {
                if (pred.test(compare.compare(rsl.get(), value))) {
                    rsl = Optional.of(value);
                }
            }
        }
        return rsl;
    }
}
