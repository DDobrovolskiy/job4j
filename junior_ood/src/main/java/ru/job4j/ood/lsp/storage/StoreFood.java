package ru.job4j.ood.lsp.storage;

import java.util.List;
import java.util.function.Predicate;

public interface StoreFood {
    boolean add(Food food);

    List<Food> getFoods();
}
