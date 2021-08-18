package ru.job4j.models;

public class Ticket {
    private int ticketId;
    private int sessionId;
    private int row;
    private int cell;
    private int price;
    private int accountId;

    public Ticket(int ticketId, int sessionId, int row, int cell, int price, int accountId) {
        this.ticketId = ticketId;
        this.sessionId = sessionId;
        this.row = row;
        this.cell = cell;
        this.price = price;
        this.accountId = accountId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}