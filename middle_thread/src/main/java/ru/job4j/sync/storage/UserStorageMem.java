package ru.job4j.sync.storage;

import net.jcip.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@ThreadSafe
public class UserStorageMem implements UserStore {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();
    @GuardedBy("this")
    private int count = 1;

    @Override
    public synchronized boolean add(User user) {
        Predicate<Integer> predicate = integer -> !users.containsKey(integer);
        Function<User, Boolean> function = userFunc -> {
            userFunc.setId(count++);
            users.put(userFunc.getId(), userFunc);
            return true;
        };
        return baseCRUD(user, predicate, function);
    }

    @Override
    public synchronized boolean update(User user) {
        Predicate<Integer> predicate = users::containsKey;
        Function<User, Boolean> function = userFunc -> {
            users.put(userFunc.getId(), userFunc);
            return true;
        };
        return baseCRUD(user, predicate, function);
    }

    @Override
    public synchronized boolean delete(User user) {
        Predicate<Integer> predicate = users::containsKey;
        Function<User, Boolean> function = userFunc -> {
            users.remove(userFunc.getId());
            return true;
        };
        return baseCRUD(user, predicate, function);
    }

    @Override
    public synchronized User find(int id) {
        User user = users.get(id);
        if (user != null) {
            return user.clone();
        }
        return null;
    }

    private boolean baseCRUD(User user,
                             Predicate<Integer> predicate,
                             Function<User, Boolean> function) {
        User userClone = getClone(user);
        if (predicate.test(userClone.getId())) {
            return function.apply(userClone);
        }
        return false;
    }

    private User getClone(User user) {
        return user.clone();
    }

}
