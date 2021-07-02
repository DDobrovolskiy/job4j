package ru.job4j.ood.lsp.parking;

public class Car implements Auto {
    @Override
    public String getTypeAuto() {
        return "car";
    }

    @Override
    public int getSizeParkingSpace() {
        return 1;
    }
}
