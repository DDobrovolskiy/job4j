package ru.job4j.models.users;

import com.google.gson.annotations.Expose;
import org.json.JSONPropertyIgnore;
import ru.job4j.models.announcements.Car;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Expose
    private int id;
    @Column(name = "user_name", nullable = false)
    @Expose
    private String name;
    @Column(name = "user_email", nullable = false, unique = true)
    @Expose
    private String email;
    @Column(name = "user_password", nullable = false)
    @Expose
    private String password;
    @Column(name = "user_phone", nullable = false)
    @Expose
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Car> cars = new LinkedList<>();

    public User() {
    }

    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        User user = (User) o;
        return id == user.id
                && Objects.equals(email, user.email)
                && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phone);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
