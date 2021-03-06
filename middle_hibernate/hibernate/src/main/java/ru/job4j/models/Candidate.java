package ru.job4j.models;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int experience;
    private int salary;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HH hh;

    public Candidate() {
    }

    public Candidate(String name, int experience, int salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public HH getHh() {
        return hh;
    }

    public void setHh(HH hh) {
        this.hh = hh;
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", experience=" + experience
                + ", salary=" + salary
                + ", hh=" + hh
                + '}';
    }
}
