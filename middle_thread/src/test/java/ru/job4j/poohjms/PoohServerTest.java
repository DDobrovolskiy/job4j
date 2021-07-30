package ru.job4j.poohjms;

import org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.*;

public class PoohServerTest {

    @Ignore
    @Test
    public void whenQueueMessage() throws IOException, InterruptedException {
        PoohServer poohServer = new PoohServer();
        Thread thread = new Thread(poohServer::start);
        thread.start();
        //Пытаемся получить сообщение из очереди
        String messageSubscriber = "GET /queue/weather HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var answerSubscriber = PoohClient.request(messageSubscriber).split("\\n");

        Assert.assertEquals(answerSubscriber[0].split("\\s")[1], "404");
        Assert.assertEquals(answerSubscriber[2], "Not found answer\r");
        //Создаем очередь
        String messagePublisher = "POST /queue/weather HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "Content-Length: 5"  +  System.lineSeparator()
                + "Content-Type: application/x-www-form-urlencoded" +  System.lineSeparator()
                + "" +  System.lineSeparator()
                + "hello";
        var answerPublisher = PoohClient.request(messagePublisher).split("\\n");

        Assert.assertEquals(answerPublisher[0].split("\\s")[1], "201");
        Assert.assertEquals(answerPublisher[2], "OK\r");
        //Получаем первое сообщение, в очереди больше нет сообщений
        var answerSubscriber1 = PoohClient.request(messageSubscriber).split("\\n");

        Assert.assertEquals(answerSubscriber1[0].split("\\s")[1], "200");
        Assert.assertEquals(answerSubscriber1[2], "hello\r");
        //Пытаемся получить второе сообщение
        var answerSubscriber2 = PoohClient.request(messageSubscriber).split("\\n");

        Assert.assertEquals(answerSubscriber2[0].split("\\s")[1], "404");
        Assert.assertEquals(answerSubscriber2[2], "Not found answer\r");

        poohServer.stop();
        thread.join();
    }

    @Ignore
    @Test
    public void whenTopicMessage() throws IOException, InterruptedException {
        PoohServer poohServer = new PoohServer();
        Thread thread = new Thread(poohServer::start);
        thread.start();
        //Subscriber [id = 1] подписывается на тему weather / сообщений нет
        String messageSubscriber1 = "GET /topic/weather/1 HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var answerSubscriber1 = PoohClient.request(messageSubscriber1).split("\\n");

        Assert.assertEquals(answerSubscriber1[0].split("\\s")[1], "404");
        Assert.assertEquals(answerSubscriber1[2], "Not found answer\r");
        //Subscriber [id = 2] подписывается на тему weather / сообщений нет
        String messageSubscriber2 = "GET /topic/weather/2 HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var answerSubscriber2 = PoohClient.request(messageSubscriber2).split("\\n");

        Assert.assertEquals(answerSubscriber2[0].split("\\s")[1], "404");
        Assert.assertEquals(answerSubscriber2[2], "Not found answer\r");
        //Publisher постит запись "hello" в тему weather
        String messagePublisher = "POST /topic/weather HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "Content-Length: 5"  +  System.lineSeparator()
                + "Content-Type: application/x-www-form-urlencoded" +  System.lineSeparator()
                + "" +  System.lineSeparator()
                + "hello";
        var answerPublisher = PoohClient.request(messagePublisher).split("\\n");

        Assert.assertEquals(answerPublisher[0].split("\\s")[1], "201");
        Assert.assertEquals(answerPublisher[2], "OK\r");
        //Subscriber [id = 1] получает собщение из темы weather / сообщение = "hello"
        String messageSubscriber1repeat = "GET /topic/weather/1 HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var answerSubscriber1repeat = PoohClient.request(messageSubscriber1repeat).split("\\n");

        Assert.assertEquals(answerSubscriber1repeat[0].split("\\s")[1], "200");
        Assert.assertEquals(answerSubscriber1repeat[2], "hello\r");
        //Subscriber [id = 2] получает собщение из темы weather / сообщение = "hello"
        String messageSubscriber2repeat = "GET /topic/weather/2 HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var answerSubscriber2repeat = PoohClient.request(messageSubscriber2repeat).split("\\n");

        Assert.assertEquals(answerSubscriber2repeat[0].split("\\s")[1], "200");
        Assert.assertEquals(answerSubscriber2repeat[2], "hello\r");
        //Subscriber [id = 1] получает собщение из темы weather / сообщений нет
        String messageSubscriber1repeat1 = "GET /topic/weather/1 HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var answerSubscriber1repeat1 = PoohClient.request(messageSubscriber1repeat1).split("\\n");

        Assert.assertEquals(answerSubscriber1repeat1[0].split("\\s")[1], "404");
        Assert.assertEquals(answerSubscriber1repeat1[2], "Not found answer\r");
        //Subscriber [id = 3] подписывается на тему weather / сообщений нет
        String messageSubscriber3 = "GET /topic/weather/3 HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var answerSubscriber3 = PoohClient.request(messageSubscriber3).split("\\n");

        Assert.assertEquals(answerSubscriber3[0].split("\\s")[1], "404");
        Assert.assertEquals(answerSubscriber3[2], "Not found answer\r");

        poohServer.stop();
        thread.join();
    }
}