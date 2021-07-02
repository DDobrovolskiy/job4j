package ru.job4j.ood.lsp.parking;

import java.util.List;
import java.util.Queue;

public interface ParkingService {
    /**@return список подключенных парковок*/
    List<ParkingSpace> getParkingSpaces();

    /**Подключение парковочных мест к сервису*/
    void addParkingSpace(ParkingSpace parkingSpace);

    /**
     * Размещает машину на парковочное место
     * @return очередь из не припаркованных авто
     * */
    List<Auto> placeAutoInParking(List<Auto> autoList);
}
