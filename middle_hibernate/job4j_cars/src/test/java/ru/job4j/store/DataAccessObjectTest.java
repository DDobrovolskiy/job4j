package ru.job4j.store;

import org.junit.*;
import ru.job4j.models.announcements.Car;
import ru.job4j.models.details.Body;
import ru.job4j.models.details.Mark;
import ru.job4j.models.users.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

public class DataAccessObjectTest {
    private final Store store = DataAccessObject.instOf();
    private Mark mark;
    private Body body;
    private User user;
    private Car car;
    private Car car2;

    @Before
    public void begin() {
        mark = new Mark("audi");
        store.addMark(mark);
        body = new Body("sedan");
        store.addBody(body);
        user = new User("test", "test", "test", "test");
        store.addUser(user);

        car = new Car();
        car.setDescription("test");
        car.setPrice(1000);
        car.setPhoto("photo");
        car.setStatus(true);
        car.setUser(user);
        car.setMark(mark);
        car.setBody(body);
        car.setCreate(Date.from(Instant.now()));

        car2 = new Car();
        car2.setDescription("test2");
        car2.setPrice(1000);
        car2.setPhoto(null);
        car2.setStatus(false);
        car2.setUser(user);
        car2.setMark(mark);
        car2.setBody(body);
        car2.setCreate(Date.from(Instant.now().minus(2, ChronoUnit.DAYS)));

        store.addCar(car2);
        store.addCar(car);
    }

    @After
    public void end() {
        store.clearCarsTable();
        store.clearBodyTable();
        store.clearMarkTable();
        store.clearUserTable();
    }

    @Test
    public void getCarsToday() {
        var result = store.getCarsToday();
        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0), is(car));
    }

    @Test
    public void getCarsWithPhoto() {
        var result = store.getCarsWithPhoto();

        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0), is(car));
    }

    @Test
    public void getCarsOfMarkOnId() {
        var result = store.getCarsOfMarkOnId(mark.getId()).get(0);
        Assert.assertThat(result.getMark(), is(car.getMark()));
    }

    @Test
    public void getBodies() {
        var result = store.getBodies();
        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0).getType(), is(body.getType()));
        Assert.assertThat(result.get(0).getId(), is(body.getId()));
    }

    @Test
    public void getMarks() {
        var result = store.getMarks();
        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0).getName(), is(mark.getName()));
        Assert.assertThat(result.get(0).getId(), is(mark.getId()));
    }

    @Test
    public void getBodyOnId() {
        var result = store.getBodyOnId(body.getId());
        Assert.assertThat(result.get().getType(), is(body.getType()));
        Assert.assertThat(result.get().getId(), is(body.getId()));
    }

    @Test
    public void getBodyOnIdBodyIsNull() {
        Assert.assertTrue(store.getBodyOnId(body.getId() + 1).isEmpty());
    }

    @Test
    public void getMarkOnId() {
        var result = store.getMarkOnId(mark.getId());
        Assert.assertThat(result.get().getName(), is(mark.getName()));
        Assert.assertThat(result.get().getId(), is(mark.getId()));
    }

    @Test
    public void getMarkOnIdBodyIsNull() {
        Assert.assertTrue(store.getMarkOnId(mark.getId() + 1).isEmpty());
    }

    @Test
    public void getAllCarStatusTrue() {
        var result = store.getAllActiveCars();
        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0), is(car));
    }

    @Test
    public void whenDeleteDataInTableCar() {
        store.clearCarsTable();
        var result = store.getAllActiveCars();
        Assert.assertThat(result.size(), is(0));
    }

    @Test
    public void whenDeleteDataInTableMark() {
        store.clearUserTable();
        var result = store.getMarks();
        Assert.assertThat(result.size(), is(0));
    }

    @Test
    public void whenDeleteDataInTableBody() {
        store.clearBodyTable();
        var result = store.getBodies();
        Assert.assertThat(result.size(), is(0));
    }

    @Test
    public void whenDeleteDataInTableUser() {
        store.clearUserTable();
        var result = store.getAllUser();
        Assert.assertThat(result.size(), is(0));
    }

    @Test
    public void getUserByEmail() {
        var result = store.getUserByEmail(user.getEmail());
        Assert.assertThat(result.get(), is(user));
    }

    @Test
    public void getCarByIdUserAll() {
        var result = store.getCarsByIdUser(user.getId());
        Assert.assertThat(result.size(), is(2));
    }

    @Test
    public void getCarByIdUserFalseId() {
        var result = store.getCarsByIdUser(0);
        Assert.assertThat(result.size(), is(0));
    }
}