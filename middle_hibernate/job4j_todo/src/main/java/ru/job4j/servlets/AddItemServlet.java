package ru.job4j.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.Category;
import ru.job4j.models.Item;
import ru.job4j.models.User;
import ru.job4j.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@WebServlet("/addItem")
public class AddItemServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddItemServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug("Begin AddItemServlet");
        req.setCharacterEncoding("UTF-8");
        String desc = req.getParameter("description");
        String[] categoriesId = req.getParameterValues("categ");
        LOG.debug("Desc: {}", desc);
        LOG.debug("categoriesId count: {}", categoriesId.length);
        Item item = new Item();
        item.setDescription(desc);
        item.setCreatedTime(Date.from(Instant.now()));
        item.setUser((User) req.getSession().getAttribute("user"));
        for (String id : categoriesId) {
            Optional<Category> category =
                    HibernateStore.instOf().findCategoryById(Integer.parseInt(id));
            category.ifPresent(item::addCategory);
        }
        LOG.debug("Item: {}", item);
        HibernateStore.instOf().save(item);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
