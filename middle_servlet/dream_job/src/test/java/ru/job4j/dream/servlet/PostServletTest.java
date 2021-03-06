package ru.job4j.dream.servlet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {

    @Test
    public void doGet() throws ServletException, IOException {
        var store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);
        var disp = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("posts.jsp")).thenReturn(disp);

        new PostServlet().doGet(req, resp);

        verify(req).getRequestDispatcher("posts.jsp");
    }

    @Test
    public void doPost() throws ServletException, IOException {
        boolean flag = false;
        String name = "name";

        var store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn(name);

        new PostServlet().doPost(req, resp);

        Assert.assertThat(store.findAllPosts().iterator().next().getName(), is(name));
    }
}