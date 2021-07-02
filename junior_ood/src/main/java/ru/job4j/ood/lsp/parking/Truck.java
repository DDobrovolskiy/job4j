package ru.job4j.ood.lsp.parking;

public class Truck implements Auto {
    @Override
    public String getTypeAuto() {
        return "Truck";
    }

    @Override
    public int getSizeParkingSpace() {
        return 3;
    }
}
