package ru.job4j.oop;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Летит по воздуху, качает крыльями");
    }

    @Override
    public int maxSpeed() {
        return 650;
    }
}
