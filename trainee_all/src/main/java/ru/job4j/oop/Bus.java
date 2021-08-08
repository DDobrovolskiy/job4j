package ru.job4j.oop;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Едет по дороге, перевозит пассажиров");
    }

    @Override
    public int maxSpeed() {
        return 50;
    }
}
