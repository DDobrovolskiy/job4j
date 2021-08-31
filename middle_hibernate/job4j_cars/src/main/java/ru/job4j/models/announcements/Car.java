package ru.job4j.models.announcements;

import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    @Column(name = "car_created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date create;
    @Column(name = "car_description", nullable = false)
    private String description;
    @Column(name = "car_price", nullable = false)
    private int price;
    @Column(name = "car_status", nullable = false)
    private boolean status;
    @Column(name = "car_photo")
    private String photo;
    @ManyToOne
    @JoinColumn(name = "mark_id", nullable = false)
    private Mark mark;
    @ManyToOne
    @JoinColumn(name = "body_id", nullable = false)
    private Body body;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
