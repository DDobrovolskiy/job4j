package ru.job4j.dream.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private LocalDateTime localDateTime;

    public Post(int id, String name, LocalDateTime localDateTime) {
        this.id = id;
        this.name = name;
        this.localDateTime = localDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
