package ru.job4j.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.servlets.ItemServlet;
import ru.job4j.servlets.LoginServlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

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
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        chain.doFilter(request, response);
    }
}
