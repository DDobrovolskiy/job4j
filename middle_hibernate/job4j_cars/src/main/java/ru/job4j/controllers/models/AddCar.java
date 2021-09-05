package ru.job4j.controllers.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.announcements.Car;
import ru.job4j.models.users.User;
import ru.job4j.store.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@WebServlet("/profile")
public class AddCar extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadPhoto.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/profile.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String description = req.getParameter("description");
            int price = Integer.parseInt(req.getParameter("price"));
            int bodyId = Integer.parseInt(req.getParameter("bodyId"));
            int markId = Integer.parseInt(req.getParameter("markId"));

            var body = DataAccessObject.instOf().getBodyOnId(bodyId).orElseThrow();
            var mark = DataAccessObject.instOf().getMarkOnId(markId).orElseThrow();
            var user = (User) req.getSession().getAttribute("user");

            Car car = new Car();
            car.setStatus(true);
            car.setDescription(description);
            car.setCreate(Date.from(Instant.now()));
            car.setPrice(price);
            car.setBody(body);
            car.setMark(mark);
            car.setUser(user);

            DataAccessObject.instOf().addCar(car);

            doGet(req, resp);
        } catch (Exception e) {
               LOG.error(e.toString());
        }
    }
}
