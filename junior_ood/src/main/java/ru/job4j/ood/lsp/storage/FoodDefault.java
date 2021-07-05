package ru.job4j.ood.lsp.storage;

import java.time.chrono.ChronoLocalDate;

public class FoodDefault implements Food {
    private final String name;
    private final ChronoLocalDate expiryDate;
    private final ChronoLocalDate createDate;
    private float price;
    private float discount;

    public FoodDefault(String name, ChronoLocalDate expiryDate,
                       ChronoLocalDate createDate, float price, float discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public ChronoLocalDate getExpiryDate() {
        return expiryDate;
    }

    public ChronoLocalDate getCreateDate() {
        return createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}