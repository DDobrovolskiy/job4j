package ru.job4j.ood.lsp.storage;

import java.time.chrono.ChronoLocalDate;

public abstract class OperationStoreBase implements OperationStore {

    private long getDeltaDays(ChronoLocalDate date1, ChronoLocalDate date2) {
        long delta = date2.toEpochDay() - date1.toEpochDay();
        if (delta < 0) {
            throw new IllegalArgumentException("Перепутаны время срока годности и создания");
        }
        return delta;
    }

    private float getPercent(long deltaDaysFood, long deltaDaysNow) {
        if (deltaDaysFood == 0) {
            throw new IllegalArgumentException("Возможно дата годности совпадает с датой создание");
        }
        return (float) deltaDaysNow / deltaDaysFood * 100;
    }

    public float getPercentFreshFood(Food food, ChronoLocalDate toDay) {
        return getPercent(
                getDeltaDays(food.getCreateDate(), food.getExpiryDate()),
                getDeltaDays(food.getCreateDate(), toDay));
    }
}
