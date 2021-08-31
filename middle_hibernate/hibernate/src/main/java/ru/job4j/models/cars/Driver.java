package ru.job4j.models.cars;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "driver_new")
public class Driver {
    @Id
    @GeneratedValue
    @Column(name = "driver_id")
    private int id;
    @ManyToMany
    private List<Car> cars = new LinkedList<>();
    @OneToOne(mappedBy = "driver")
    private Owner owner;
    @Column(name = "driver_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
