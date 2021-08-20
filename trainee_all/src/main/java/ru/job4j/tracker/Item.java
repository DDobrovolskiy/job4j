package ru.job4j.tracker;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now();
    private String description;
    private Timestamp createdTime;

    public Item() {

    }

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public Item(int id,
                String name,
                LocalDateTime created,
                String description,
                Timestamp createdTime) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.description = description;
        this.createdTime = createdTime;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", created=" + created
                + ", description='" + description + '\''
                + ", createdTime=" + createdTime
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        boolean rsl = id == item.id
                && Objects.equals(name, item.name);
        return rsl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}