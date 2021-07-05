package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ControlQuality {

    private ChronoLocalDate toDay = LocalDate.now();
    private final List<OperationStore> operationStores;

    public ControlQuality(List<OperationStore> operationStores) {
        this.operationStores = operationStores;
    }

    public void setSortedDay(ChronoLocalDate toDay) {
        this.toDay = toDay;
    }

    public void sortedFoodInStore(List<Food> foods) {
        for (Food food : foods) {
            boolean flag = false;
            for (OperationStore operationStore : operationStores) {
                if (operationStore.add(food, toDay)) {
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

    public void resort() {
        List<Food> foods = new LinkedList<>();
        operationStores.forEach(operationStore -> foods.addAll(operationStore.extract()));
        sortedFoodInStore(foods);
    }

}
