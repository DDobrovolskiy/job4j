package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;

public interface Food {
    String getName();

    LocalDate getExpiryDate();

    LocalDate getCreateDate();

    float getPrice();

    void setPrice(float price);

    float getDiscount();

    void setDiscount(float discount);
}
