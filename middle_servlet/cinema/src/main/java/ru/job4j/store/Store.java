package ru.job4j.store;

import ru.job4j.models.Account;
import ru.job4j.models.Message;
import ru.job4j.models.Ticket;

import java.util.Collection;

public interface Store {
    Collection<Ticket> getTickets();

    Ticket[][] getTicketsMatrix();

    Ticket findTicketById(int id);

    Account findAccountByPhone(String phone);

    Account findAccountById(int id);

    Account saveAccount(Account account);

    Message payTicket(int ticketId, int accountId);
}

