package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements ParkingSpace {
    private final int parkingSpace;
    private List<Auto> auto = new ArrayList<>();

    public Parking(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    @Override
    public int getFreeParkingSpace() {
        return 0;
    }

    @Override
    public boolean takeParkingSpace(Auto auto) {
        return false;
    }

    @Override
    public List<Auto> getAutoOnParking() {
        return null;
    }
}
