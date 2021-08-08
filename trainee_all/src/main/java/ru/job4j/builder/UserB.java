package ru.job4j.builder;

import java.util.Comparator;

public class UserB {
    private String name;
    private int id;
    private int count;
    private int good;
    private Comparator comparator;
    private String notify;
    private String end;

    public static Builder userB() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "UserB{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", count=" + count
                + ", good=" + good
                + ", notify='" + notify + '\''
                + ", end='" + end + '\''
                + '}';
    }

    public static class Builder {
        private String name;
        private int id;
        private int count;
        private int good;
        private Comparator comparator;
        private String notify;
        private String end;

        public UserB build() {
            UserB userB = new UserB();
            userB.name = name;
            userB.id = id;
            userB.count = count;
            userB.good = good;
            userB.comparator = comparator;
            userB.notify = notify;
            userB.end = end;
            return userB;
        }

        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildCount(int count) {
            this.count = count;
            return this;
        }

        public Builder buildGood(int good) {
            this.good = good;
            return this;
        }

        public Builder build(Comparator comparator) {
            this.comparator = comparator;
            return this;
        }

        public Builder buildNotify(String notify) {
            this.notify = notify;
            return this;
        }

        public Builder buildEnd(String end) {
            this.end = end;
            return this;
        }
    }
}
