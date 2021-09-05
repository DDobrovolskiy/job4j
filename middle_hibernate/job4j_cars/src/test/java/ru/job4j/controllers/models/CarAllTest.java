package ru.job4j.controllers.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.*;
import ru.job4j.models.announcements.Car;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;
import ru.job4j.store.DataAccessObject;
import ru.job4j.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

public class CarAllTest {
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    private final Store store = DataAccessObject.instOf();
    private Mark mark;
    private Body body;
    private User user;
    private Car car;
    private Car car2;

    @Before
    public void begin() {
        mark = new Mark("audi");
        store.addMark(mark);
        body = new Body("sedan");
        store.addBody(body);
        user = new User("test", "test", "test", "test");
        store.addUser(user);

        car = new Car();
        car.setDescription("test");
        car.setPrice(1000);
        car.setPhoto("photo");
        car.setStatus(true);
        car.setUser(user);
        car.setMark(mark);
        car.setBody(body);
        car.setCreate(Date.from(Instant.now()));

        car2 = new Car();
        car2.setDescription("test2");
        car2.setPrice(1000);
        car2.setPhoto(null);
        car2.setStatus(false);
        car2.setUser(user);
        car2.setMark(mark);
        car2.setBody(body);
        car2.setCreate(Date.from(Instant.now().minus(2, ChronoUnit.DAYS)));

        store.addCar(car2);
        store.addCar(car);
    }

    @After
    public void end() {
        store.clearCarsTable();
        store.clearBodyTable();
        store.clearMarkTable();
        store.clearUserTable();
    }

    @Test
    public void doGet() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var stringWrite = new StringWriter();
        var out = new PrintWriter(stringWrite);
        when(resp.getWriter()).thenReturn(out);

        new CarAll().doGet(req, resp);

        String result = stringWrite.getBuffer().toString();

        stringWrite.close();
        out.close();

        System.out.println(result);

        Assert.assertThat(result, is(GSON.toJson(store.getAllActiveCars()) + "\r\n"));
    }
}