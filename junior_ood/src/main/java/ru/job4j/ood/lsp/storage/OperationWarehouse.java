package ru.job4j.ood.lsp.storage;

public class OperationWarehouse extends OperationStoreBase implements OperationStore {
    private StoreFood storeFood;

    public OperationWarehouse(StoreFood storeFood) {
        this.storeFood = storeFood;
    }

    @Override
    public boolean add(Food food) {
        if (getPercentFreshFood(food) < 25) {
            return storeFood.add(food);
        }
        return false;
    }
}
