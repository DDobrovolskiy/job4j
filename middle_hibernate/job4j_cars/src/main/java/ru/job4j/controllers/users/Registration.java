package ru.job4j.controllers.users;

import ru.job4j.models.users.User;
import ru.job4j.store.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/reg")
public class Registration extends HttpServlet {
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
        String phone = req.getParameter("phone");
        var userOptional = DataAccessObject.instOf().getUserByEmail(email);
        try {
            if (userOptional.isEmpty()) {
                User user = new User();
                user.setId(0);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                DataAccessObject.instOf().addUser(user);
                req.getSession().setAttribute("user", user);
                resp.addCookie(new Cookie("email", email));
                resp.addCookie(new Cookie("password", password));
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                doGet(req, resp);
            }
        } catch (Exception e) {
            doGet(req, resp);
        }
    }
}
