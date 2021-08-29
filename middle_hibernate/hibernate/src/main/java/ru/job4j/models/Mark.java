package ru.job4j.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private int id;
    @Column(name = "mark_name")
    private String name;
    @OneToMany(mappedBy = "mark", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    public static Mark of(String name) {
        Mark mark = new Mark();
        mark.setName(name);
        return mark;
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

    public void addCar(Car car) {
        cars.add(car);
    }

    @Override
    public String toString() {
        return "Mark{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", cars=" + cars
                + '}';
    }
}
