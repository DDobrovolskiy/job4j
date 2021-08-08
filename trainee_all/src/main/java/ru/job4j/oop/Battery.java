package ru.job4j.oop;

public class Battery {
    private int load;

    public Battery(int load) {
        this.load = load;
    }

    public void exchange(Battery another) {
        another.load += this.load;
        this.load = 0;
    }

    public static void main(String[] args) {
        Battery battery1 = new Battery(15);
        Battery battery2 = new Battery(50);
        System.out.println("Load: " + battery1.load + "%");
        battery2.exchange(battery1);
        System.out.println("Charging...");
        System.out.println("Load: " + battery1.load + "%");
    }
}
