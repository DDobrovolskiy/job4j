package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Analizy {
    private boolean flagOutdoor = false;
    private List<String> serverOutdoor = new ArrayList<>();

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines().forEach(line -> {
                if ((line.startsWith("500") || line.startsWith("400")) && !getFlagOutdoorServer()) {
                    setFlagOutdoorServer(true);
                    serverOutdoor.add(line.split(" ")[1] + ";");
                }
                if ((line.startsWith("200") || line.startsWith("300")) && getFlagOutdoorServer()) {
                    setFlagOutdoorServer(false);
                    int lastIndex = serverOutdoor.size() - 1;
                    serverOutdoor.set(
                            lastIndex, serverOutdoor.get(lastIndex) + line.split(" ")[1] + ";");
                }
            });
            writeResult(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeResult(String target) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            serverOutdoor.forEach(out::println);
        }
    }

    private boolean getFlagOutdoorServer() {
        return flagOutdoor;
    }

    private void setFlagOutdoorServer(boolean flagOutdoor) {
        this.flagOutdoor = flagOutdoor;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
