package ru.job4j.ood.lsp.storage;

import java.time.chrono.ChronoLocalDate;
import java.util.LinkedList;
import java.util.List;

public class OperationWarehouse extends OperationStoreBase implements OperationStore {
    private final StoreFood storeFood;

    public OperationWarehouse(StoreFood storeFood) {
        this.storeFood = storeFood;
    }

    @Override
    public boolean add(Food food, ChronoLocalDate toDay) {
        if (getPercentFreshFood(food, toDay) < 25) {
            return storeFood.add(food);
        }
        return false;
    }

    @Override
    public List<Food> extract() {
        List<Food> rsl = new LinkedList<>(storeFood.getFoods());
        storeFood.clear();
        return rsl;
    }
}
