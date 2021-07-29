package ru.job4j.sync.storage;

import net.jcip.annotations.*;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorageMem implements UserStore {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        User userClone = user.clone();
        if (!users.containsKey(userClone.getId())) {
            users.put(userClone.getId(), userClone);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean update(User user) {
        User userClone = user.clone();
        if (users.containsKey(userClone.getId())) {
            users.put(userClone.getId(), userClone);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean delete(User user) {
        User userClone = user.clone();
        if (users.containsKey(userClone.getId())) {
            users.remove(userClone.getId());
            return true;
        }
        return false;
    }

    @Override
    public synchronized User find(int id) {
        User user = users.get(id);
        if (user != null) {
            return user.clone();
        }
        return null;
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = find(fromId);
        User userTo = find(toId);
        if (userFrom != null && userTo != null) {
            if (userFrom.getAmount() >= amount) {
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
                update(userFrom);
                update(userTo);
                result = true;
            }
        }
        return result;
    }
}
