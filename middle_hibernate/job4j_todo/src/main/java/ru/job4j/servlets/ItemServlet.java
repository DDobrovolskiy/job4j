package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.Item;
import ru.job4j.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@WebServlet("/index")
public class ItemServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ItemServlet.class.getName());
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug("Start doGet");
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        Collection<Item> items = null;
        String checked = req.getParameter("all");
        if (checked == null) {
            checked = "false";
        }
        if (checked.equals("false")) {
            items = HibernateStore.instOf().getOnlyDidntDoneItem();
        } else {
            items = HibernateStore.instOf().getAllItem();
        }
        LOG.debug("Checked: {}", checked);
        LOG.debug("Items get: {}", items);
        String json = GSON.toJson(items);
        LOG.debug("doGet object JSON: {}", json);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug("Begin doPost");
        Item item = GSON.fromJson(req.getReader(), Item.class);
        LOG.debug("id item: {} - is done: {}", item.getId(), item.isDone());
        HibernateStore.instOf().updateDone(item.getId(), item.isDone());
    }
}
