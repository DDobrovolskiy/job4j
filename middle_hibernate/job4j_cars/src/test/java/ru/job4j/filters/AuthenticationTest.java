package ru.job4j.filters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.announcements.Car;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;
import ru.job4j.store.DataAccessObject;
import ru.job4j.store.Store;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthenticationTest {

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
    public void doFilterGoToLogin() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var chain = mock(FilterChain.class);

        when(req.getRequestURI()).thenReturn("/login");

        new Authentication().doFilter(req, resp, chain);

        verify(chain).doFilter(req, resp);
    }

    @Test
    public void doFilterGoToRegistration() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var chain = mock(FilterChain.class);

        when(req.getRequestURI()).thenReturn("/reg");

        new Authentication().doFilter(req, resp, chain);

        verify(chain).doFilter(req, resp);
    }

    @Test
    public void doFilterAuthentication() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var chain = mock(FilterChain.class);
        var session = mock(HttpSession.class);

        when(req.getRequestURI()).thenReturn("/another");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user"))
                .thenReturn(new User("test", "test", "test", "test"));

        new Authentication().doFilter(req, resp, chain);

        verify(chain).doFilter(req, resp);
    }

    @Test
    public void doFilterAuthenticationCookiesIsNull() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var chain = mock(FilterChain.class);
        var session = mock(HttpSession.class);

        when(req.getRequestURI()).thenReturn("/another");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(req.getContextPath()).thenReturn("");

        Cookie[] cookies = new Cookie[]{};

        when(req.getCookies()).thenReturn(cookies);

        new Authentication().doFilter(req, resp, chain);

        verify(resp).sendRedirect("/login");
    }

    @Test
    public void doFilterAuthenticationCookies() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var chain = mock(FilterChain.class);
        var session = mock(HttpSession.class);

        when(req.getRequestURI()).thenReturn("/another");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        Cookie[] cookies = new Cookie[]{
                new Cookie("email", "test"),
                new Cookie("password", "test")};

        when(req.getCookies()).thenReturn(cookies);

        new Authentication().doFilter(req, resp, chain);

        verify(chain).doFilter(req, resp);
    }
}