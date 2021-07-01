package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Store implements StoreFood {
    private final List<Food> foodDefaults = new ArrayList<>();

    @Override
    public List<Food> getFoods() {
        return foodDefaults;
    }

    @Override
    public boolean add(Food food) {
        return foodDefaults.add(food);
    }
}