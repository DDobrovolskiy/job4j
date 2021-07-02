package ru.job4j.ood.lsp.parking;

import java.util.LinkedList;
import java.util.List;

public class Service implements ParkingService {
    private List<ParkingSpace> parkings = new LinkedList<>();

    @Override
    public List<ParkingSpace> getParkingSpaces() {
        return parkings;
    }

    @Override
    public void addParkingSpace(ParkingSpace parkingSpace) {
        parkings.add(parkingSpace);
    }

    @Override
    public List<Auto> placeAutoInParking(List<Auto> autoList) { //TODO можно оптимизировать
        List<Auto> autoRemaining = new LinkedList<>();
        for (Auto auto : autoList) {
            boolean flag = false;
            for (ParkingSpace parking : parkings) {
                if (parking.takeParkingSpace(auto)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                autoRemaining.add(auto);
            }
        }
        return autoRemaining;
    }
}
