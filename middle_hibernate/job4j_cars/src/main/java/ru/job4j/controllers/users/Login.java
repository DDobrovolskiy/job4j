package ru.job4j.controllers.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.store.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(Login.class.getName());

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

        var userOptional = DataAccessObject.instOf().getUserByEmail(email);
        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(password)) {
                HttpSession sc = req.getSession();
                sc.setAttribute("user", userOptional.get());
                resp.addCookie(new Cookie("email", email));
                resp.addCookie(new Cookie("password", password));
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                doGet(req, resp);
            }
        } else {
            doGet(req, resp);
        }
    }

}
