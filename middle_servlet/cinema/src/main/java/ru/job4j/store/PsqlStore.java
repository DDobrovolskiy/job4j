package ru.job4j.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.Account;
import ru.job4j.models.Message;
import ru.job4j.models.Ticket;
import ru.job4j.property.PropertyFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = PropertyFactory.load("db.properties");
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            LOG.error("Ошибка загрузки драйвера базы данных: ", e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "SELECT "
                             + "ticketId, sessionId, row, cell, price, "
                             + "COALESCE(accountId, 0) as accountId "
                             + "FROM ticket")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    tickets.add(new Ticket(
                            it.getInt("ticketId"),
                            it.getInt("sessionId"),
                            it.getInt("row"),
                            it.getInt("cell"),
                            it.getInt("price"),
                            it.getInt("accountId")));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return tickets;
    }

    @Override
    public Ticket[][] getTicketsMatrix() {
        Collection<Ticket> tickets = getTickets();
        int row = 0;
        int cell = 0;
        for (Ticket ticket : tickets) {
            if (row < ticket.getRow()) {
                row = ticket.getRow();
            }
            if (cell < ticket.getCell()) {
                cell = ticket.getCell();
            }
        }
        Ticket[][] ticketsMatrix = new Ticket[row][cell];
        for (Ticket ticket : tickets) {
            ticketsMatrix[ticket.getRow() - 1][ticket.getCell() - 1] = ticket;
        }
        return ticketsMatrix;
    }

    @Override
    public Account findAccountById(int id) {
        Account account = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement(
                             "SELECT accountId, name, phone "
                                     + "FROM account "
                                     + "WHERE accountId = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    LOG.warn("Не найден account с id = {}", id);
                } else {
                    account = new Account(
                            resultSet.getInt("accountId"),
                            resultSet.getString("name"),
                            resultSet.getString("phone"));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return account;
    }

    @Override
    public Ticket findTicketById(int id) {
        Collection<Ticket> tickets = getTickets();
        Ticket ticket = null;
        for (Ticket ticketSearch : tickets) {
            if (ticketSearch.getTicketId() == id) {
                ticket = ticketSearch;
                break;
            }
        }
        return ticket;
    }

    @Override
    public Account findAccountByPhone(String phone) {
        Account account = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement(
                             "SELECT accountId, name, phone "
                                     + "FROM account "
                                     + "WHERE phone = ?")) {
            statement.setString(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    LOG.warn("Не найден account с phone = {}", phone);
                } else {
                    account = new Account(
                            resultSet.getInt("accountId"),
                            resultSet.getString("name"),
                            resultSet.getString("phone"));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return account;
    }

    @Override
    public Account saveAccount(Account account) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement(
                             "INSERT INTO account(name, phone) VALUES (?, ?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, account.getName());
            ps.setString(2, account.getPhone());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    account.setAccountId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Ошибка создания данных SQL: ", e);
        }
        return account;
    }

    @Override
    public Message payTicket(int ticketId, int accountId) {
        Message message = new Message(false, "Покупка не прошла");
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement(
                             "UPDATE ticket SET accountid = ? "
                                     + "WHERE ticketid = ? and accountid IS NULL")) {
            statement.setInt(1, accountId);
            statement.setInt(2, ticketId);
            var res = statement.executeUpdate();
            LOG.debug("Update test {}", res);
            if (res == 1) {
                message.setMessage("Покупка прошла успешно!");
                message.setFlag(true);
            }
        } catch (Exception e) {
            LOG.error("Ошибка получения данных SQL: ", e);
        }
        return message;
    }
}
