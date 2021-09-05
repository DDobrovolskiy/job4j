package ru.job4j.controllers.users;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.models.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServletTest {
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Test
    public void doGet() throws IOException, ServletException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);

        var stringWrite = new StringWriter();
        var out = new PrintWriter(stringWrite);

        User user = new User("test", "test", "test", "test");

        when(resp.getWriter()).thenReturn(out);
        when(session.getAttribute("user"))
                .thenReturn(user);

        new UserServlet().doGet(req, resp);

        String result = stringWrite.getBuffer().toString();

        stringWrite.close();
        out.close();

        System.out.println(result);

        Assert.assertThat(result, is(GSON.toJson(user) + "\r\n"));
    }

    @Test
    public void doPost() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var session = mock(HttpSession.class);

        var cookies = new Cookie[]{new Cookie("test", "test")};

        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("");
        when(req.getCookies()).thenReturn(cookies);

        new UserServlet().doPost(req, resp);

        System.out.println(cookies[0].getValue());

        verify(resp).sendRedirect("/login");
    }
}