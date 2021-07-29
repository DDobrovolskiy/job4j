package ru.job4j.poohjms;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReqTest {

    @Test
    public void whenQueueMessage() {
        String message = "POST /queue/weather HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "Content-Length: 5"  +  System.lineSeparator()
                + "Content-Type: application/x-www-form-urlencoded" +  System.lineSeparator()
                + "" +  System.lineSeparator()
                + "hello";
        //System.out.println(message);
        var req = Req.of(message);
        //System.out.println(req.method());
        //System.out.println(req.mode());
        //System.out.println(req.theme());
        //System.out.println(req.id());
        //System.out.println(req.text());
        Assert.assertEquals(req.method(), "POST");
        Assert.assertEquals(req.mode(), "queue");
        Assert.assertEquals(req.theme(), "weather");
        Assert.assertEquals(req.text(), "hello");
    }

    @Test
    public void whenQueueMessageGet() {
        String message = "GET /queue/weather HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var req = Req.of(message);
        Assert.assertEquals(req.method(), "GET");
        Assert.assertEquals(req.mode(), "queue");
        Assert.assertEquals(req.theme(), "weather");
    }

    @Test
    public void whenTopicMessage() {
        String message = "POST /topic/weather HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "Content-Length: 5"  +  System.lineSeparator()
                + "Content-Type: application/x-www-form-urlencoded" +  System.lineSeparator()
                + "" +  System.lineSeparator()
                + "hello";
        var req = Req.of(message);
        Assert.assertEquals(req.method(), "POST");
        Assert.assertEquals(req.mode(), "topic");
        Assert.assertEquals(req.theme(), "weather");
        Assert.assertEquals(req.text(), "hello");
    }

    @Test
    public void whenTopicMessageGet() {
        String message = "GET /topic/weather/1 HTTP/1.1" +  System.lineSeparator()
                + "Host: localhost:9000" +  System.lineSeparator()
                + "User-Agent: curl/7.78.0" +  System.lineSeparator()
                + "Accept: */*" +  System.lineSeparator()
                + "" +  System.lineSeparator();
        var req = Req.of(message);
        Assert.assertEquals(req.method(), "GET");
        Assert.assertEquals(req.mode(), "topic");
        Assert.assertEquals(req.theme(), "weather");
        Assert.assertThat(1, is(req.id()));
    }
}