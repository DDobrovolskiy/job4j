package ru.job4j.ood.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParkingTest {

    @Test (expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithNegativeNumber() {
        ParkingSpace parking = new Parking(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenCreateParkingSpaceWithZero() {
        ParkingSpace parking = new Parking(0);
    }

    @Test
    public void whenCreateParkingNormal() {
        ParkingSpace parking = new Parking(10);
        int actualFreeParkingSpace = 10;
        Assert.assertThat(actualFreeParkingSpace, is(parking.getFreeParkingSpace()));
    }

    @Test (expected = IllegalArgumentException.class)
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
        Assert.assertTrue(parking.takeParkingSpace(auto));
        int actualFreeParkingSpace = 5;
        Assert.assertThat(actualFreeParkingSpace, is(parking.getFreeParkingSpace()));
        int countAutoInParking = 1;
        Assert.assertThat(countAutoInParking, is(parking.getAutoOnParking().size()));
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
        Assert.assertTrue("1 машина", parking.takeParkingSpace(auto));
        Assert.assertTrue("2 машина", parking.takeParkingSpace(auto));
        Assert.assertFalse("3 машина", parking.takeParkingSpace(auto));
        int actualFreeParkingSpace = 0;
        Assert.assertThat("Просранство", actualFreeParkingSpace, is(parking.getFreeParkingSpace()));
        int countAutoInParking = 2;
        Assert.assertThat(
                "3 машина попытка 2",
                countAutoInParking,
                is(parking.getAutoOnParking().size()));
    }
}