package ru.job4j.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Dmitriy";
        int age = 30;
        int year = 2021;
        int day = 50;
        String error = "Critical exceptions";
        String message = "Stop this day...";
        String admin = "Test";
        float rsl = 5F;
        double time = 10.45252;

        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("Year now: {}, on learning java: {} days", year, day);
        LOG.info("Admin: {}, result:{} on time all: {}", admin, rsl, time);
        LOG.warn("Warning message: {}", message);
        LOG.error("Error message: {}", error);
    }
}
