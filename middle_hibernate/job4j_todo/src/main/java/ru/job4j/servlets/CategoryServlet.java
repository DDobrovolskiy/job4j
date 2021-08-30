package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.models.Category;
import ru.job4j.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> categories = HibernateStore.instOf().findAllCategories();
        String gson = GSON.toJson(categories);
        var out = resp.getOutputStream();
        out.write(gson.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
        //resp.sendRedirect(req.getContextPath() + "/");
    }
}
