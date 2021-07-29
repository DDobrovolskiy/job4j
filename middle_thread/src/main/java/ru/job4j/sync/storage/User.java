package ru.job4j.sync.storage;

public class User {
    private final int id;
    private int amount;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User clone() {
        User user = new User(this.id);
        user.amount = this.amount;
        return user;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }
}
