package ru.job4j.models.cars;

import javax.persistence.*;

@Entity
@Table(name = "owner_new")
public class Owner {
    @Id
    @GeneratedValue
    @Column(name = "owner_id")
    private int id;
    @ManyToOne
    private Car car;
    @OneToOne
    private Driver driver;
    @Column(name = "owner_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
