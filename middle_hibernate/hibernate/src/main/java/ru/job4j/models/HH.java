package ru.job4j.models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "headhunter")
public class HH {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hh", fetch = FetchType.LAZY)
    private Candidate candidate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hh", fetch = FetchType.LAZY)
    private List<Vacancy> vacancies = new LinkedList<>();

    public HH() {
    }

    public HH(String name) {
        this.name = name;
    }

    public void add(Vacancy vacancy) {
        vacancies.add(vacancy);
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

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public String toString() {
        return "HH{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", vacancies=" + vacancies
                + '}';
    }
}
