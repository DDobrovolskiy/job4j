package ru.job4j.models.announcements;

import com.google.gson.annotations.Expose;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    @Expose
    private int id;
    @Column(name = "car_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Expose
    private Date create;
    @Column(name = "car_description", nullable = false)
    @Expose
    private String description;
    @Column(name = "car_price", nullable = false)
    @Expose
    private int price;
    @Column(name = "car_status", nullable = false)
    @Expose
    private boolean status;
    @Column(name = "car_photo")
    @Expose
    private String photo;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mark_id", nullable = false)
    @Expose
    private Mark mark;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "body_id", nullable = false)
    @Expose
    private Body body;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    @Expose
    private User user;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && price == car.price
                && status == car.status
                && Objects.equals(description, car.description)
                && Objects.equals(photo, car.photo)
                && Objects.equals(mark, car.mark)
                && Objects.equals(body, car.body)
                && Objects.equals(user, car.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                description,
                price,
                status,
                photo,
                mark,
                body,
                user);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", create=" + create
                + ", description='" + description + '\''
                + ", price=" + price
                + ", status=" + status
                + ", photo='" + photo + '\''
                + ", mark=" + mark
                + ", body=" + body
                + ", user=" + user
                + '}';
    }
}
