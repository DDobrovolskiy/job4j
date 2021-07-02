package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface ParkingSpace {
    /**Возращает количество свободных парковочных мест*/
    int getFreeParkingSpace();

    /**Занимает парковочное место*/
    boolean takeParkingSpace(Auto auto);

    /**Возращает список авто на парковке*/
    List<Auto> getAutoOnParking();
}
