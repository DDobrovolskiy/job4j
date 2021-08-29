package ru.job4j.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.User;
import ru.job4j.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/reg.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        var userOptional = HibernateStore.instOf().findUserByEmail(email);
        if (!userOptional.isPresent()) {
            User user = new User();
            user.setId(0);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            HibernateStore.instOf().addUser(user);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            doGet(req, resp);
        }
    }
}
