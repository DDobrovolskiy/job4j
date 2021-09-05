package ru.job4j.controllers.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.store.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/models/active")
public class CarStatus extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CarStatus.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        LOG.info("id - {}", id);
        LOG.info("Status - {}", status);
        DataAccessObject.instOf().changeStatusCar(id, status);
    }
}
