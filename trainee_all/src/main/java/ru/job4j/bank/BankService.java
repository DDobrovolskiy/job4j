package ru.job4j.bank;

import ru.job4j.inheritance.Pacient;

import java.util.*;

/**
 * Класс описывает работу простейшего банковского перевода
 * @author Dmitriy Dobrovolskiy
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей осуществляется в коллекции типа HashMap
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Добавляет пользователя в коллекцию
     * @param user пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Добавляет счет "{@param account}" к пользователю "{@link User}"
     * по номеру паспорта
     * @param passport номер паспорта
     * @param account счет
     */
    public void addAccount(String passport, Account account) {
            Optional<User> user = findByPassport(passport);
            if (user.isPresent()) {
                if (!users.get(user.get()).contains(account)) {
                    users.get(user.get()).add(account);
                }
            }
    }

    /**
     * Находит пользователя "{@link User}" по данным его паспорта
     * если пользователя нет в колекции возвращает {@code null}
     * @param passport номер паспорта
     * @return возвращает ссылку на пользователя
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Находит счет по данным паспорта пользователя "{@link User}"
     * и реквизитам счета {@link Account} привязанным к пользователю
     * если счета по данным реквизита нет в колекции возвращает {@code null}
     * @param passport данные паспорта
     * @param requisite реквизита счета {@link Account}
     * @return возращает ссылку на счет
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
            Optional<User> user = findByPassport(passport);
            if (user.isPresent()) {
                return users.get(user.get()).stream()
                        .filter(account -> account.getRequisite().equals(requisite))
                        .findFirst();
            }
            return null;
    }

    /**
     * Обеспечивет трансфер денежный средст от одного пользователя к другому
     * по данным паспортов и реквизитам счетов
     * при этом обеспечивается валидация операции,
     * при успешном прохождении оперци возращается {@code true},
     * при нехватки средств, отсутствии счета или пользователя в коллекциях
     * возращается {@code false}
     * @param srcPassport паспортные данные отправителя
     * @param srcRequisite реквизиты счета отправителя
     * @param destPassport паспортные данные получателя
     * @param destRequisite реквизиты счета получателя
     * @param amount размер денежных средств перевода
     * @return возращает валидацию операции
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Optional<Account> accountSrc = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> accountDest = findByRequisite(destPassport, destRequisite);
        if (accountSrc.isEmpty() && accountDest.isEmpty()) {
            return false;
        } else if (accountSrc.get().getBalance() < amount) {
            System.out.println("Pff... your need money");
            return false;
        }
        accountSrc.get().setBalance(accountSrc.get().getBalance() - amount);
        accountDest.get().setBalance(accountDest.get().getBalance() + amount);
        return true;
    }

    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        String requisite = "3fdsbb9";
        accounts.add(new Account("3fdsbb9", 140));
        int index = accounts.indexOf(new Account(requisite, -1));
        Account find = accounts.get(index);
        System.out.println(find.getRequisite() + " -> " + find.getBalance());
    }
}
