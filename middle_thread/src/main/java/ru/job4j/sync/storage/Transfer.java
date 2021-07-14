package ru.job4j.sync.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Transfer {
    @GuardedBy("this")
    private final UserStore userStore;

    public Transfer(UserStore userStore) {
        this.userStore = userStore;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = userStore.find(fromId);
        User userTo = userStore.find(toId);
        return doTrans(userFrom, userTo, amount);
    }

    private boolean doTrans(User userFrom, User userTo, int amount) {
        boolean result = false;
        if (isNotNull(userFrom) && isNotNull(userTo)) {
            result = checkAmount(userFrom, userTo, amount);
        }
        return result;
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
            } else {
                //rollback
                amountTrans(userTo, userFrom, amount);
            }
        }
        return result;
    }

    private void amountTrans(User userFrom, User userTo, int amount) {
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
    }

    private synchronized boolean checkUpdate(User userFrom, User userTo) {
        return userStore.update(userFrom) && userStore.update(userTo);
    }
}
