package ru.job4j.oop;

public class Vehicles {
    public static void main(String[] args) {
        Vehicle bus = new Bus();
        Vehicle airplane = new Airplane();
        Vehicle train = new Train();
        Vehicle[] vehicles = new Vehicle[] {bus, airplane, train};
        int maxSpeed = 0;
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
            if (maxSpeed < vehicle.maxSpeed()) {
                maxSpeed = vehicle.maxSpeed();
            }
        }
        System.out.println("Самый быстрый транспорт еде со скоротью: " + maxSpeed);
    }
}
