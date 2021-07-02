package ru.job4j.ood.lsp.parking;

import java.util.Queue;

public interface ParkingService {
    /**Подключение парковочных мест к сервису*/
    void addParkingSpace(ParkingSpace parkingSpace);

    /**
     * Размещает машину на парковочное место
     * @return очередь из не припаркованных авто
     * */
    Queue<Auto> placeAutoInParking(Queue<Auto> autoList);
}
