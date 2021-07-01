package ru.job4j.ood.lsp.storage;

public class OperationShop extends OperationStoreBase implements OperationStore {
    private StoreFood storeFood;

    public OperationShop(StoreFood storeFood) {
        this.storeFood = storeFood;
    }

    @Override
    public boolean add(Food food) {
        float percent = getPercentFreshFood(food);
        if ((percent >= 25) && (percent < 75)) {
            return storeFood.add(food);
        } else if ((percent >= 75) && (percent < 100)) {
            food.setDiscount(10);
            return storeFood.add(food);
        }
        return false;
    }
}
