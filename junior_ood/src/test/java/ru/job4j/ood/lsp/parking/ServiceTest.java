package ru.job4j.ood.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void whenParkingServiceWork() {
        ParkingService service = new Service();
        ParkingSpace parking = new Parking(1, 3);
        service.addParkingSpace(parking);
        int actualCountParkingSpace = 1;
        Assert.assertThat(
                "Количество парковок",
                actualCountParkingSpace,
                is(service.getParkingSpaces().size()));
        Auto car = new Car();
        Auto truck = new Truck(3);
        List<Auto> auto = new LinkedList<>();
        auto.add(truck);
        auto.add(truck);
        auto.add(truck);
        auto.add(truck);
        auto.add(truck);
        auto.add(car);
        //all parking space 13
        auto = service.placeAutoInParking(auto); //В очереди остается 1 машина
        //all parking space 3
        int actualCountAutoInList = 2;
        Assert.assertThat("Оставшихся авто 2", actualCountAutoInList, is(auto.size()));
        int actualCountAutoInParking = 4;
        Assert.assertThat(
                "Машин на парковке 4",
                actualCountAutoInParking,
                is(service.getParkingSpaces().get(0).getAutoOnParking().size()));
        //Всего на парковке 4 машины
        service.addParkingSpace(new Parking(3, 1)); //Добавляем еще один паркинг
        auto = service.placeAutoInParking(auto); //Все машины распределены
        actualCountAutoInList = 0;
        Assert.assertThat("Оставшихся авто 0", actualCountAutoInList, is(auto.size()));
    }
}