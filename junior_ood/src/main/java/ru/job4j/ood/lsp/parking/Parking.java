package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements ParkingSpace {
    private int parkingSpace;
    private List<Auto> autoList = new ArrayList<>();

    public Parking(int parkingSpace) {
        this.parkingSpace = validateSpace(parkingSpace);
    }

    private int validateSpace(int space) {
        if (space <= 0) {
            throw new IllegalArgumentException("Парковочное место отрицательно или равно 0");
        }
        return space;
    }

    @Override
    public int getFreeParkingSpace() {
        return parkingSpace;
    }

    @Override
    public boolean takeParkingSpace(Auto auto) {
        int autoSpace = validateSpace(auto.getSizeParkingSpace());
        if (parkingSpace >= autoSpace) {
            parkingSpace -= autoSpace;
            autoList.add(auto);
            return true;
        }
        return false;
    }

    @Override
    public List<Auto> getAutoOnParking() {
        return autoList;
    }
}
