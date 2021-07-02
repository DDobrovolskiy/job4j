package ru.job4j.ood.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test //(expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithNegativeNumber() {
        ParkingSpace parking = new Parking(-1);
    }

    @Test //(expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithZero() {
        ParkingSpace parking = new Parking(0);
    }

    @Test
    public void whenCreateParkingNormal() {
        ParkingSpace parking = new Parking(10);
        int actualFreeParkingSpace = 10;
        //Assert.assertEquals(parking.getFreeParkingSpace(), actualFreeParkingSpace);
    }

    @Test //(expected = IllegalArgumentException.class)
    public void whenWrongParkingSpaceAuto() {
        ParkingSpace parking = new Parking(10);
        Auto auto = new Auto() {
            @Override
            public String getTypeAuto() {
                return "test";
            }

            @Override
            public int getSizeParkingSpace() {
                return -1;
            }
        };
        parking.takeParkingSpace(auto);
    }

    @Test
    public void whenTakeParkingSpaceAutoNotFull() {
        ParkingSpace parking = new Parking(10);
        Auto auto = new Auto() {
            @Override
            public String getTypeAuto() {
                return "test";
            }

            @Override
            public int getSizeParkingSpace() {
                return 5;
            }
        };
        //Assert.assertTrue(parking.takeParkingSpace(auto));
        int actualFreeParkingSpace = 5;
        //Assert.assertEquals(parking.getFreeParkingSpace(), actualFreeParkingSpace);
        int countAutoInParking = 1;
        //Assert.assertEquals(parking.getAutoOnParking().size(), countAutoInParking);
    }

    @Test
    public void whenTakeParkingSpaceAutoFull() {
        ParkingSpace parking = new Parking(10);
        Auto auto = new Auto() {
            @Override
            public String getTypeAuto() {
                return "test";
            }

            @Override
            public int getSizeParkingSpace() {
                return 5;
            }
        };
        //Assert.assertTrue(parking.takeParkingSpace(auto));
        //Assert.assertTrue(parking.takeParkingSpace(auto));
        //Assert.assertFalse(parking.takeParkingSpace(auto));
        int actualFreeParkingSpace = 0;
        //Assert.assertEquals(parking.getFreeParkingSpace(), actualFreeParkingSpace);
        int countAutoInParking = 2;
        //Assert.assertEquals(parking.getAutoOnParking().size(), countAutoInParking);
    }
}