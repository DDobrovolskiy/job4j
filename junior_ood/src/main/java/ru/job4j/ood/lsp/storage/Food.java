package ru.job4j.ood.lsp.storage;

import java.time.chrono.ChronoLocalDate;

public interface Food {
    String getName();

    ChronoLocalDate getExpiryDate();

    ChronoLocalDate getCreateDate();

    float getPrice();

    void setPrice(float price);

    float getDiscount();

    void setDiscount(float discount);
}
