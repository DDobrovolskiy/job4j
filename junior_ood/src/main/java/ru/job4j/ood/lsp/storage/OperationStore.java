package ru.job4j.ood.lsp.storage;

import java.time.chrono.ChronoLocalDate;
import java.util.List;

public interface OperationStore {
    boolean add(Food food, ChronoLocalDate toDay);

    List<Food> extract();
}
