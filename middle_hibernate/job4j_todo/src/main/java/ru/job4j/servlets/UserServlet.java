package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class.getName());
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(req.getSession().getAttribute("user"));
        LOG.debug("doGet object JSON: {}", json);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
