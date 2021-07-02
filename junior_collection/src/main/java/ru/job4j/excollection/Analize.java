package ru.job4j.excollection;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int added = current.size();
        int deleted = previous.size();

        Map<Integer, User> mapFirst = new HashMap<>(previous.size());
        previous.forEach(user -> mapFirst.put(user.id, user));

        for (User user : current) {
            if (mapFirst.containsKey(user.id)) {
                added--;
                deleted--;
                if (!Objects.equals(mapFirst.get(user.id).name, user.name)) {
                    changed++;
                }
            }
        }

       // Set<User> userAdd = new HashSet<>(current);
       // Set<User> userDel = new HashSet<>(previous);
       // Set<User> userChange = userAdd;
       //
       // userAdd.removeAll(new HashSet<>(previous));
       // int added = userAdd.size();
       //
       // userDel.removeAll(new HashSet<>(current));
       // int deleted = userDel.size();

        return new Info(added, changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

       // @Override
       // public boolean equals(Object o) {
       //     if (this == o) {
       //         return true;
       //     }
       //     if (o == null || getClass() != o.getClass()) {
       //         return false;
       //     }
       //     User user = (User) o;
       //     return id == user.id;
       // }
//
       // @Override
       // public int hashCode() {
       //     return Objects.hash(id);
       // }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
