package ru.job4j.collection.ex;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;

public class UserEmailTest {

    @Test
    public void mergeUsersUniqueEmailAndUser() {
        Map<String, List<String>> users = new HashMap<>();

        users.put("user0", List.of("1@one.net"));
        users.put("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));

        UserEmail userEmail = new UserEmail();
        Map<String, List<String>> actual = userEmail.mergeUser(users);

        for (Map.Entry<String, List<String>> item : actual.entrySet()) {
            if (Set.of("user1").contains(item.getKey())) {
                Assert.assertTrue(
                        item.getValue()
                                .containsAll(Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
                continue;
            }
            if (Set.of("user0").contains(item.getKey())) {
                Assert.assertTrue(
                        item.getValue()
                                .containsAll(Set.of("1@one.net")));
                continue;
            }
            Assert.assertTrue(false);
        }
    }

    @Test
    public void mergeUsersOnEmail() {
        Map<String, List<String>> users = new HashMap<>();

        users.put("user0", List.of("1@one.net"));
        users.put("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        users.put("user2", List.of("foo@gmail.com", "ups@pisem.net"));
        users.put("user3", List.of("xyz@pisem.net", "vasya@pupkin.com", "1@one.net"));
        users.put("user4", List.of("ups@pisem.net", "aaa@bbb.ru"));
        users.put("user5", List.of("xyz@pisem.net"));

        UserEmail userEmail = new UserEmail();
        Map<String, List<String>> actual = userEmail.mergeUser(users);

        for (Map.Entry<String, List<String>> item : actual.entrySet()) {
            if (Set.of("user1", "user2", "user4").contains(item.getKey())) {
                Assert.assertTrue(
                        item.getValue()
                                .containsAll(Set.of(
                                        "xxx@ya.ru",
                                        "foo@gmail.com",
                                        "lol@mail.ru",
                                        "ups@pisem.net",
                                        "aaa@bbb.ru")));
                continue;
            }
            if (Set.of("user3", "user5", "user0").contains(item.getKey())) {
                Assert.assertTrue(
                        item.getValue()
                        .containsAll(Set.of("xyz@pisem.net", "vasya@pupkin.com", "1@one.net")));
                continue;
            }
            Assert.assertTrue(false);
        }
    }
}