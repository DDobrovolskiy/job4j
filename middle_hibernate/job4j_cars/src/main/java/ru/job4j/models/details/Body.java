package ru.job4j.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import org.json.JSONPropertyIgnore;
import ru.job4j.models.announcements.Car;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bodies")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_id")
    @Expose
    private int id;
    @Column(name = "body_type", nullable = false, unique = true)
    @Expose
    private String type;
    @OneToMany(mappedBy = "body", cascade = CascadeType.ALL)
    private List<Car> cars = new LinkedList<>();

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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return id == body.id && Objects.equals(type, body.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Body{"
                + "id=" + id
                + ", type='" + type + '\''
                + '}';
    }
}
