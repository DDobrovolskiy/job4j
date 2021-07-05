package ru.job4j.ood.lsp.storage;

import java.time.chrono.ChronoLocalDate;
import java.util.LinkedList;
import java.util.List;

public class OperationShop extends OperationStoreBase implements OperationStore {
    private final StoreFood storeFood;

    public OperationShop(StoreFood storeFood) {
        this.storeFood = storeFood;
    }

    @Override
    public boolean add(Food food, ChronoLocalDate toDay) {
        float percent = getPercentFreshFood(food, toDay);
        if ((percent >= 25) && (percent < 75)) {
            return storeFood.add(food);
        } else if ((percent >= 75) && (percent < 100)) {
            food.setDiscount(10);
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
