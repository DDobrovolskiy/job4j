package ru.job4j.controllers.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.models.users.User;
import ru.job4j.store.DataAccessObject;
import ru.job4j.store.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/models/carsusers")
public class CarsUser extends HttpServlet {
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Store store = DataAccessObject.instOf();
        var user = (User) req.getSession().getAttribute("user");
        String carAll = GSON.toJson(store.getCarsByIdUser(user.getId()));
        var out = resp.getOutputStream();
        out.write(carAll.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
