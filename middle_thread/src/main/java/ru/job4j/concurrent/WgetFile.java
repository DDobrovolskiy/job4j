package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class WgetFile implements Runnable {
    private final String url;
    private final int speed;
    private long start;
    private long sleep;

    public WgetFile(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             //BufferedInputStream in = new BufferedInputStream(new FileInputStream(url));
             FileOutputStream fileOutputStream = new FileOutputStream("load.txt")) {
            int sizeBufByte = 4096;
            byte[] dataBuffer = new byte[sizeBufByte];
            long deltaByte = 0;
            int bytesRead;
            long start = System.nanoTime();
            System.out.println(start);
            while ((bytesRead = in.read(dataBuffer, 0, sizeBufByte)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                deltaByte += bytesRead;
                System.out.println(deltaByte);
                double deltaTime = getDeltaTime();
                System.out.println("delta time: " + deltaTime);
                Thread.sleep(getSleepMill(deltaTime, bytesRead));
            }

        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private double getDeltaTime() {
        double deltaTime = (double) (System.nanoTime() - start) / 1_000_000_000;
        start = System.nanoTime();
        return deltaTime;
    }

    private void printSpeed(double deltaTime, int bytesRead) {
        double speedNow = bytesRead / deltaTime;
        System.out.println("Speed: " + speedNow);
    }

    private long getSleepMill(double deltaTime, int bytesRead) {
        printSpeed(deltaTime, bytesRead);
        sleep += (long) ((((double) bytesRead / speed) - deltaTime) * 1000);
        if (sleep < 0) {
            sleep = 0;
        }
        System.out.println("Sleep: " + sleep);
        return this.sleep;
    }

    public static void main(String[] args) throws InterruptedException {
        //String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        //int speed = 16;
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new WgetFile(url, speed));
        wget.start();
        wget.join();
    }
}
