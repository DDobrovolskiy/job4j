package ru.job4j.dream.servlet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class CandidateServletTest {

    @Test
    public void doGet() throws ServletException, IOException {
        var store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var disp = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("candidates.jsp")).thenReturn(disp);

        new CandidateServlet().doGet(req, resp);

        verify(req).getRequestDispatcher("candidates.jsp");
    }

    @Test
    public void doPost() throws ServletException, IOException {
        boolean flag = false;
        String name = "name";

        var store = MemStore.instOf();

        Assert.assertFalse(flag);

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn(name);
        when(req.getParameter("city")).thenReturn("1");

        new CandidateServlet().doPost(req, resp);

        Assert.assertThat(store.findAllCandidates().iterator().next().getName(), is(name));
    }
}