package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements ParkingSpace {
    private int spaceForCar;
    private int spaceForTruck;
    private List<Auto> autoList = new ArrayList<>();

    public Parking(int spaceForCar, int spaceForTruck) {
        this.spaceForCar = validateSpace(spaceForCar);
        this.spaceForTruck = validateSpace(spaceForTruck);
    }

    private int validateSpace(int space) throws IllegalArgumentException {
        if (space <= 0) {
            throw new IllegalArgumentException("Парковочное место отрицательно или равно 0");
        }
        return space;
    }

    private boolean addAutoOnSpaceForCar(Auto auto) {
        int space = validateSpace(auto.getSizeParkingSpace());
        if (spaceForCar >= space) {
            spaceForCar -= space;
            autoList.add(auto);
            return true;
        }
        return false;
    }

    private boolean addAutoOnSpaceForTruck(Auto auto) {
        if (spaceForTruck > 0) {
            spaceForTruck--;
            autoList.add(auto);
            return true;
        } else {
            return addAutoOnSpaceForCar(auto);
        }
    }

    private boolean isTruck(Auto auto) throws IllegalArgumentException {
        return validateSpace(auto.getSizeParkingSpace()) > 1;
    }

    @Override
    public int getFreeParkingSpaceForCar() {
        return spaceForCar;
    }

    @Override
    public int getFreeParkingSpaceForTruck() {
        return spaceForTruck;
    }

    @Override
    public boolean takeParkingSpace(Auto auto) throws IllegalArgumentException {
        if (isTruck(auto)) {
            //truck
            return addAutoOnSpaceForTruck(auto);
        } else {
            //car
            return addAutoOnSpaceForCar(auto);
        }
    }

    @Override
    public List<Auto> getAutoOnParking() {
        return autoList;
    }
}
