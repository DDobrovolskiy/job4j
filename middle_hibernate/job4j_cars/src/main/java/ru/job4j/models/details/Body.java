package ru.job4j.models.details;

import javax.persistence.*;

@Entity
@Table(name = "bodies")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_id")
    private int id;
    @Column(name = "body_type", nullable = false)
    private String type;

    public Body() {
    }

    public Body(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Body{"
                + "id=" + id
                + ", type='" + type + '\''
                + '}';
    }
}
