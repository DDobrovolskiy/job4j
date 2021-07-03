package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface ParkingSpace {
    /**Возращает количество свободных парковочных мест для Машин*/
    int getFreeParkingSpaceForCar();

    /**Возращает количество свободных парковочных мест для Грузовиков*/
    int getFreeParkingSpaceForTruck();

    /**Занимает парковочное место
     * @return возращает флаг припарковано авто или нет*/
    boolean takeParkingSpace(Auto auto);

    /**Возращает список авто на парковке*/
    List<Auto> getAutoOnParking();
}
