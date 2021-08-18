package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.Account;
import ru.job4j.models.Message;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TicketServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TicketServlet.class.getName());
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("idTic"));
        LOG.debug("doGet id = {}", id);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(PsqlStore.instOf().findTicketById(id));
        LOG.debug("doGet object JSON: {}", json);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int ticketId = Integer.parseInt(req.getParameter("ticketId"));
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");

        LOG.debug("doPost ticket: {}", ticketId);
        LOG.debug("doPost username: {}", username);
        LOG.debug("doPost phone: {}", phone);

        Account account = PsqlStore.instOf().findAccountByPhone(phone);
        if (account == null) {
            account = PsqlStore.instOf().saveAccount(new Account(0, username, phone));
        }

        Message message = PsqlStore.instOf().payTicket(ticketId, account.getAccountId());

        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(message);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
