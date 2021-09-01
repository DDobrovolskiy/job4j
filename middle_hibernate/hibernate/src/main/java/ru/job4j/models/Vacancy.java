package ru.job4j.models;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private HH hh;

    public Vacancy() {
    }

    public Vacancy(String name, HH hh) {
        this.name = name;
        this.hh = hh;
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

    public HH getHh() {
        return hh;
    }

    public void setHh(HH hh) {
        this.hh = hh;
    }

    @Override
    public String toString() {
        return "Vacancy{"
            + "id=" + id
            + ", name='" + name + '\''
            + '}';
    }
}
