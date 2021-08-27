package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class.getName());
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.debug(req.getParameter("email"));
        LOG.debug(req.getParameter("password"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        var userOptional = HibernateStore.instOf().findUserByEmail(email);
        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(password)) {
                HttpSession sc = req.getSession();
                sc.setAttribute("user", userOptional.get());
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                doGet(req, resp);
            }
        } else {
            doGet(req, resp);
        }
    }
}
