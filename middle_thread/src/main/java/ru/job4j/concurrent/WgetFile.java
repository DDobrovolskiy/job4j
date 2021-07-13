package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class WgetFile implements Runnable {
    private final String url;
    private final String out;
    private final int speed;

    public WgetFile(String url, String out, int speed) {
        this.url = url;
        this.out = out;
        this.speed = speed;
    }

    @Override
    public void run() {

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             //BufferedInputStream in = new BufferedInputStream(new FileInputStream(url));
             FileOutputStream fileOutputStream = new FileOutputStream(out)) {
            int sizeBufByte = 1024;
            byte[] dataBuffer = new byte[sizeBufByte];
            int bytesRead;
            long start = System.nanoTime();
            long sleep = 0;
            while ((bytesRead = in.read(dataBuffer, 0, sizeBufByte)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                double deltaTime = getDeltaTime(start, System.nanoTime());
                start = System.nanoTime(); //Новое начало отсчета
                System.out.println("delta time: " + deltaTime);
                sleep += changeSleepTime(deltaTime, bytesRead);
                System.out.println("Sleep: " + sleep);
                Thread.sleep(sleep);
            }

        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /**
     * Расчет разницы во времени
     * @param start начало отсчета в наносек.
     * @param end окончание отсчета в наносек.
     * @return разница времени мужду началом замера и окончанием в сек.
     * */
    private double getDeltaTime(long start, long end) {
        return (double) (end - start) / 1_000_000_000;
    }

    private void printSpeed(double deltaTime, int bytesRead) {
        double speedNow = bytesRead / deltaTime;
        System.out.println("Speed: " + speedNow);
    }

    /**
     * Расчет на сколько количественно необходимо изменить время сна потока с учетом
     * текущей скорости и ограничивающей скорости скачивания {@link WgetFile#speed}
     * @param deltaTime период времени, за который происходит расчет
     * @param bytesRead количество прочитанных байтов за период времени.
     * @return количественное изменение времени сна в млсек.
     * */
    private long changeSleepTime(double deltaTime, int bytesRead) {
        printSpeed(deltaTime, bytesRead);
        return (long) ((((double) bytesRead / speed) - deltaTime) * 1000);
    }

    private static String validate(String param) {
        if (param == null) {
            throw new IllegalArgumentException("Не хватает параметров");
        }
        return param;
    }

    public static void main(String[] args) throws InterruptedException {
        String url = validate(args[0]);
        String out = validate(args[1]);
        int speed = Integer.parseInt(validate(args[2]));
        Thread wget = new Thread(new WgetFile(url, out, speed));
        wget.start();
        wget.join();
    }
}
