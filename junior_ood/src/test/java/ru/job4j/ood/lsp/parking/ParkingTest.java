package ru.job4j.ood.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class ParkingTest {

    @Test (expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithNegativeNumberCar() {
        ParkingSpace parking = new Parking(-1, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithZeroCar() {
        ParkingSpace parking = new Parking(0, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithNegativeNumberTruck() {
        ParkingSpace parking = new Parking(10, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithZeroTruck() {
        ParkingSpace parking = new Parking(10, 0);
    }

    @Test
    public void whenCreateParkingNormal() {
        ParkingSpace parking = new Parking(10, 10);
        int actualFreeParkingSpaceForCar = 10;
        Assert.assertThat(actualFreeParkingSpaceForCar, is(parking.getFreeParkingSpaceForCar()));
        int actualFreeParkingSpaceForTruck = 10;
        Assert.assertThat(
                actualFreeParkingSpaceForTruck,
                is(parking.getFreeParkingSpaceForTruck()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongParkingSpaceAuto() {
        ParkingSpace parking = new Parking(10, 10);
        Auto auto = new Truck(-1);
        parking.takeParkingSpace(auto);
    }

    @Test
    public void whenTakeParkingSpaceAutoNotFull() {
        ParkingSpace parking = new Parking(10, 10);
        Auto car = new Car();
        Auto truck = new Truck(5);
        Assert.assertTrue(parking.takeParkingSpace(car));
        int actualFreeParkingSpaceCar = 9;
        Assert.assertThat(actualFreeParkingSpaceCar, is(parking.getFreeParkingSpaceForCar()));

        Assert.assertTrue(parking.takeParkingSpace(truck));
        int actualFreeParkingSpaceTruck = 9;
        Assert.assertThat(actualFreeParkingSpaceTruck, is(parking.getFreeParkingSpaceForTruck()));

        int countAutoInParking = 2;
        Assert.assertThat(countAutoInParking, is(parking.getAutoOnParking().size()));
    }

    @Test
    public void whenTakeParkingSpaceAutoFull() {
        ParkingSpace parking = new Parking(4, 2);
        Auto truck = new Truck(5);
        Assert.assertTrue("1 машина", parking.takeParkingSpace(truck));
        Assert.assertTrue("2 машина", parking.takeParkingSpace(truck));
        Assert.assertFalse("3 машина", parking.takeParkingSpace(truck));
        int actualFreeParkingSpace = 0;
        Assert.assertThat(
                "Просранство",
                actualFreeParkingSpace,
                is(parking.getFreeParkingSpaceForTruck()));
        int countAutoInParking = 2;
        Assert.assertThat(
                "3 машина попытка 2",
                countAutoInParking,
                is(parking.getAutoOnParking().size()));
    }
}