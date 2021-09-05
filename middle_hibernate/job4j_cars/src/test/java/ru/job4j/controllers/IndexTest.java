package ru.job4j.controllers;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexTest {

    @Test
    public void doGet() throws ServletException, IOException {
        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var disp = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/index.html")).thenReturn(disp);

        new Index().doGet(req, resp);

        verify(req).getRequestDispatcher("/index.html");
    }
}