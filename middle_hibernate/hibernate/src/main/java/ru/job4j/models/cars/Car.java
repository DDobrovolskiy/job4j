package ru.job4j.models.cars;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "cars_new")
public class Car {
    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private int id;
    @ManyToOne
    private Engine engine;
    @ManyToMany(mappedBy = "cars")
    private List<Driver> drivers = new LinkedList<>();
    @OneToMany(mappedBy = "car")
    private List<Owner> owners = new LinkedList<>();
    @Column(name = "car_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
