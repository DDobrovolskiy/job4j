package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {

    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void show() {
        System.out.println("Active: " + active);
        System.out.println("ID: " + status);
        System.out.println(message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        error.show();
        Error error1 = new Error(true, 404, "Page not found");
        error1.show();
        Error error2 = new Error(false, 112, "Stupid monkey");
        error2.show();
    }
}
