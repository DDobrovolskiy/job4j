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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class CandidateServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        boolean flag = false;
        String name = "name";

        var store = MemStore.instOf();

        //Проверяем что изночально в мемсторе нету такого имени
        for (Candidate candidate : store.findAllCandidates()) {
            if (candidate.getName().equals(name)) {
                flag = true;
                break;
            }
        }
        Assert.assertFalse(flag);

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        var req = mock(HttpServletRequest.class);
        var resp = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn(name);

        new CandidateServlet().doPost(req, resp);

        //Теперь должно быть
        for (Candidate candidate : store.findAllCandidates()) {
            if (candidate.getName().equals(name)) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(flag);
    }
}