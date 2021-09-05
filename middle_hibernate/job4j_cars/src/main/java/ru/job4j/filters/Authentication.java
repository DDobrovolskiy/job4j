package ru.job4j.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.store.DataAccessObject;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class Authentication extends HttpFilter {
    private static final Logger LOG = LoggerFactory.getLogger(Authentication.class.getName());

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("Begin filter Authentication");
        var request = (HttpServletRequest) req;
        var response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        if (uri.endsWith("/login") || uri.endsWith("/reg")) {
            chain.doFilter(request, response);
            return;
        }

        var session = request.getSession();

        if (session.getAttribute("user") == null) {
            String email = null;
            String password = null;
            var cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            if ((email != null) && (password != null)) {
                var userOptional = DataAccessObject.instOf().getUserByEmail(email);
                if (userOptional.isPresent()) {
                    if (userOptional.get().getPassword().equals(password)) {
                        session.setAttribute("user", userOptional.get());
                        chain.doFilter(request, response);
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        chain.doFilter(request, response);
    }
}

