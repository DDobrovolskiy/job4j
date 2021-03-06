package ru.job4j.sync.storage;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStorageMemTest {

    @Test
    public void whenTransferWorkInMultiThread() throws InterruptedException {
        User user1 = new User(1);
        user1.setAmount(500);
        User user2 = new User(2);
        User user3 = new User(3);
        User user4 = new User(4);

        UserStore userStore = new UserStorageMem();
        Assert.assertTrue(userStore.add(user1)); //id = 1
        Assert.assertTrue(userStore.add(user2)); //id = 2
        Assert.assertTrue(userStore.add(user3)); //id = 3
        Assert.assertTrue(userStore.add(user4)); //id = 4

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                Assert.assertTrue(userStore.transfer(1, 2, 1));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                Assert.assertTrue(userStore.transfer(1, 3, 1));
            }
        });

        Thread thread3 = new Thread(() -> {
            userStore.delete(userStore.find(4));
            Assert.assertFalse(userStore.transfer(1, 4, 1));
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("User1 amount: " + userStore.find(1));
        System.out.println("User2 amount: " + userStore.find(2));
        System.out.println("User3 amount: " + userStore.find(3));

        Assert.assertThat(100, is(userStore.find(1).getAmount()));
        Assert.assertThat(200, is(userStore.find(2).getAmount()));
        Assert.assertThat(200, is(userStore.find(3).getAmount()));
    }
}