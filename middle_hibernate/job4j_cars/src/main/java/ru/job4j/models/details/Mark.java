package ru.job4j.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import org.json.JSONPropertyIgnore;
import org.json.JSONPropertyName;
import ru.job4j.models.announcements.Car;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    @Expose
    private int id;
    @Column(name = "mark_name", nullable = false, unique = true)
    @Expose
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mark")
    private List<Car> cars = new LinkedList<>();

    public Mark() {
    }

    public Mark(String name) {
        this.name = name;
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
        Mark mark = (Mark) o;
        return id == mark.id && Objects.equals(name, mark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Mark{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
