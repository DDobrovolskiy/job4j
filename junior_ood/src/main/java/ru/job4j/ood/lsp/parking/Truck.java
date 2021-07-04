package ru.job4j.ood.lsp.parking;

public class Truck implements Auto {

    private final int space;

    public Truck(int space) {
        this.space = space;
    }

    @Override
    public int getSizeParkingSpace() {
        return space;
    }
}
