package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HallServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(HallServlet.class.getName());
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //resp.setHeader("Access-Control-Allow-Origin", "*");
        //resp.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        //String json = GSON.toJson(PsqlStore.instOf().getTickets());
        String json = GSON.toJson(PsqlStore.instOf().getTicketsMatrix());
        LOG.debug("doGet object JSON: {}", json);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
