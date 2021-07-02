package ru.job4j.ood.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void whenParkingServiceWork() {
        ParkingService service = new Service();
        ParkingSpace parking = new Parking(10);
        service.addParkingSpace(parking);
        int actualCountParkingSpace = 1;
        //Assert.assertEquals(service.getParkingSpaces().size(), actualCountParkingSpace);
        Auto car = new Auto() {
            @Override
            public String getTypeAuto() {
                return "car";
            }

            @Override
            public int getSizeParkingSpace() {
                return 1;
            }
        };
        Auto truck = new Auto() {
            @Override
            public String getTypeAuto() {
                return "truck";
            }

            @Override
            public int getSizeParkingSpace() {
                return 3;
            }
        };
        List<Auto> auto = new LinkedList<>();
        auto.add(truck);
        auto.add(truck);
        auto.add(truck);
        auto.add(truck);
        auto.add(car);
        //all parking space 13
        auto = service.placeAutoInParking(auto); //В очереди остается 1 машина
        //all parking space 3
        int actualCountAutoInList = 1;
        //Assert.assertEquals(auto.size(), actualCountAutoInList);
        int actualCountAutoInParking = 4;
        //Assert.assertEquals(
        //        service.getParkingSpaces().get(0).getAutoOnParking().size(),
        //        actualCountAutoInParking); //Всего на парковке 4 машины
        service.addParkingSpace(new Parking(10)); //Добавляем еще один паркинг
        auto = service.placeAutoInParking(auto); //Все машины распределены
        actualCountAutoInList = 0;
        //Assert.assertEquals(auto.size(), actualCountAutoInList);
    }
}