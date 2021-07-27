package ru.job4j.sync.storage;

import net.jcip.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@ThreadSafe
public class UserStorageMem implements UserStore {
    private final Object lock = new Object();
    @GuardedBy("lock")
    private final Map<Integer, User> users = new HashMap<>();
    @GuardedBy("lock")
    private int count = 1;

    private UserStorageMem() {
    }

    //Что бы не ускользвл this используем фабрику
    public static UserStore factory() {
        return new UserStorageMem();
    }

    //Болокировка происходит в методе baseCRUD, объект блокировки "lock"
    @Override
    public boolean add(User user) {
        Predicate<Integer> predicate = integer -> !users.containsKey(integer);
        Function<User, Boolean> function = userFunc -> {
            userFunc.setId(count++);
            users.put(userFunc.getId(), userFunc);
            return true;
        };
        return baseCRUD(user, predicate, function);
    }

    @Override
    public boolean update(User user) {
        Predicate<Integer> predicate = users::containsKey;
        Function<User, Boolean> function = userFunc -> {
            users.put(userFunc.getId(), userFunc);
            return true;
        };
        return baseCRUD(user, predicate, function);
    }

    @Override
    public boolean delete(User user) {
        Predicate<Integer> predicate = users::containsKey;
        Function<User, Boolean> function = userFunc -> {
            users.remove(userFunc.getId());
            return true;
        };
        return baseCRUD(user, predicate, function);
    }

    @Override
    public User find(int id) {
        User user;
        synchronized (lock) {
            user = users.get(id);
        }
        if (user != null) {
            return user.clone();
        }
        return null;
    }

    //Болокировка происходит в методе doTrans, объект блокировки "lock"
    @Override
    public boolean transfer(int fromId, int toId, int amount) {
        User userFrom = find(fromId);
        User userTo = find(toId);
        return doTrans(userFrom, userTo, amount);
    }

    private boolean doTrans(User userFrom, User userTo, int amount) {
        synchronized (lock) {
            boolean result = false;
            if (isNotNull(userFrom) && isNotNull(userTo)) {
                result = checkAmount(userFrom, userTo, amount);
            }
            return result;
        }
    }

    private boolean isNotNull(User user) {
        return user != null;
    }

    private boolean checkAmount(User userFrom, User userTo, int amount) {
        boolean result = false;
        if (userFrom.getAmount() >= amount) {
            //do
            amountTrans(userFrom, userTo, amount);
            //validate
            if (checkUpdate(userFrom, userTo)) {
                result = true;
            }
        }
        return result;
    }

    private void amountTrans(User userFrom, User userTo, int amount) {
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
    }

    private synchronized boolean checkUpdate(User userFrom, User userTo) {
        return update(userFrom) && update(userTo);
    }

    private boolean baseCRUD(User user,
                             Predicate<Integer> predicate,
                             Function<User, Boolean> function) {
        synchronized (lock) {
            User userClone = getClone(user);
            if (predicate.test(userClone.getId())) {
                return function.apply(userClone);
            }
            return false;
        }
    }

    private User getClone(User user) {
        return user.clone();
    }

}