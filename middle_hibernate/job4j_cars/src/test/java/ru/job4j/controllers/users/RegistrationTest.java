package ru.job4j.controllers.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.announcements.Car;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;
import ru.job4j.store.DataAccessObject;
import ru.job4j.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistrationTest {
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
        var disp = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/reg.html")).thenReturn(disp);

        new Registration().doGet(req, resp);

        verify(req).getRequestDispatcher("/reg.html");
    }

    @Test
    public void doPostWhenNewUser() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var session = mock(HttpSession.class);

        when(req.getParameter("email")).thenReturn("test2");
        when(req.getParameter("password")).thenReturn("test2");
        when(req.getParameter("name")).thenReturn("test2");
        when(req.getParameter("phone")).thenReturn("test2");
        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("");

        new Registration().doPost(req, resp);

        verify(resp).sendRedirect("/");
    }

    @Test
    public void doPostWhenOldUserInsertOldEmail() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);

        when(req.getParameter("email")).thenReturn("test");
        when(req.getParameter("password")).thenReturn("test2");
        when(req.getParameter("name")).thenReturn("test2");
        when(req.getParameter("phone")).thenReturn("test2");

        when(req.getContextPath()).thenReturn("");

        var desp = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/reg.html")).thenReturn(desp);

        new Registration().doPost(req, resp);

        verify(req).getRequestDispatcher("/reg.html");
    }

    @Test
    public void doPostWhenOldUserInsertOldPhone() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);

        when(req.getParameter("email")).thenReturn("test2");
        when(req.getParameter("password")).thenReturn("test2");
        when(req.getParameter("name")).thenReturn("test2");
        when(req.getParameter("phone")).thenReturn("test");

        when(req.getContextPath()).thenReturn("");

        var desp = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/reg.html")).thenReturn(desp);

        new Registration().doPost(req, resp);

        verify(req).getRequestDispatcher("/reg.html");
    }
}