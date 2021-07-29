package ru.job4j.poohjms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PoohClient {
    public static String request(String message) {
        StringBuilder answer = new StringBuilder();
        try (Socket client = new Socket("localhost", 9000)) {
            try (var in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 var out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
                out.write(message);
                out.flush();
                in.lines().forEach(s -> {
                    answer.append(s).append(System.lineSeparator());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer.toString();
    }
}
