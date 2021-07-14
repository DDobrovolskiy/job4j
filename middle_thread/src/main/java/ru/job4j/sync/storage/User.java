package ru.job4j.sync.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

@ThreadSafe
public class User {
    @GuardedBy("this")
    private int id;
    @GuardedBy("this")
    private int amount;

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized int getAmount() {
        return amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public synchronized User clone() {
        User user = new User();
        user.id = this.id;
        user.amount = this.amount;
        return user;
    }

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public synchronized String toString() {
        return "User{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }
}
