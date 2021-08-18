package ru.job4j.models;

public class Account {
    private int accountId;
    private String name;
    private String phone;

    public Account(int accountId, String name, String phone) {
        this.accountId = accountId;
        this.name = name;
        this.phone = phone;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
