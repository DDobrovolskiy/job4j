package ru.job4j.collection.ex;

import java.util.*;

public class UserEmail {

    private Map<String, List<String>> mergeUsers;
    private Map<String, Node> uniqueUser = new HashMap<>();
    private Map<String, Node> uniqueEmail = new HashMap<>();

    public Map<String, List<String>> mergeUser(Map<String, List<String>> users) {
        mergeUsers = new HashMap<>(users.size());
        for (Map.Entry<String, List<String>> user : users.entrySet()) {
            //O(n)
            for (String email : user.getValue()) {
                //O(n + m)
                add(user.getKey(), email);
            }
        }
        return mergeUsers;
    }

    private void add(String user, String email) {
        if (uniqueEmail.containsKey(email)) {
                //Объединить двух узеров у кого есть такой емайл
            merge(user, email);
        } else {
                //Вставить к юзеру емайл, так как пока емайл уникальный
            insert(user, email);
        }
    }

    private void merge(String user, String email) {
        Node nodeEmail = uniqueEmail.get(email); //По емайлу узнаем у кого есть такой же емайл
        if (uniqueUser.containsKey(user)) {
            //Если такой юзер есть, вытаскиваем его запись
            Node nodeUser = uniqueUser.get(user);
            //Проверяем если записи равны, то ничего делать не надо,
            //так как в этом юзере уже есть нужный емал
            //если нет, значит в Map mergeUser присутствуют записи, которые необходимо слить
            if (!nodeUser.equals(nodeEmail)) {
                mergeInMapMergeUser(nodeUser.name, nodeEmail.name);
            }
        } else {
            uniqueUser.put(user, nodeEmail); //Если такой юзер еще не пресутствовал добавить
            //в список с ссылкой на юзера у которого есть такой емайл
            //емайл вставлять не надо так как он есть у этого юзера
        }
    }

    private void mergeInMapMergeUser(String user1, String user2) {
        //Сливаем емайлы юзеров
        mergeUsers.merge(user1, mergeUsers.get(user2), (email1, email2) -> {
            email1.addAll(email2);
            return email1;
        });
        mergeUsers.remove(user2); //Удаляем юзера2 из итогового списка
        Node nodeUser1 = uniqueUser.get(user1); //Ссылка на юзера 1
        uniqueUser.put(user2, nodeUser1); //Переписываем ссылку юзера 2 на юзер 1
    }

    private void insert(String user, String email) {
        //Получаем или создаем запись на юзера
        Node node = getNode(user);
        //Добавляем к списку уникальных емайлов запись
        uniqueEmail.put(email, node);
        //Вставляем в итоговый список емайл
        insertInMapMergeUser(node.name, email);
    }

    private void insertInMapMergeUser(String user, String email) {
        if (mergeUsers.containsKey(user)) {
            mergeUsers.get(user).add(email);
        } else {
            mergeUsers.put(user, new ArrayList<>(List.of(email)));
        }
    }

    private Node getNode(String user) {
        if (!uniqueUser.containsKey(user)) {
            uniqueUser.put(user, new Node(user));
        }
        return uniqueUser.get(user);
    }

    static class Node {
        private String name;

        Node(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
