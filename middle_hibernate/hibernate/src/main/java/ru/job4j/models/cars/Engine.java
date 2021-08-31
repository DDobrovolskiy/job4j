package ru.job4j.models.cars;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "engine_new")
public class Engine {
    @Id
    @GeneratedValue
    @Column(name = "engine_id")
    private int id;
    @OneToMany(mappedBy = "engine")
    private List<Car> cars;
    @Column(name = "engine_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Car> getCar() {
        return cars;
    }

    public void setCar(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
