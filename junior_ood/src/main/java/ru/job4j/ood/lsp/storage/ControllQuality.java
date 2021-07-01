package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllQuality {

    private final List<OperationStore> operationStores;

    public ControllQuality(List<OperationStore> operationStores) {
        this.operationStores = operationStores;
    }

    public void sortedFoodInStore(List<Food> foods) {
        for (Food food : foods) {
            Boolean flag = false;
            for (OperationStore operationStore : operationStores) {
                if (operationStore.add(food)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println(food.getName() + " распределен");
            } else {
                throw new IllegalArgumentException(food.getName() + " не распределен!");
            }
        }
    }

}
