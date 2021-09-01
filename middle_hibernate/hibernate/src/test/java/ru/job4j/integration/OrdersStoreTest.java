package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./src/main/db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void destroyTable() throws SQLException {
        pool.getConnection().prepareStatement("DROP TABLE orders").executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindByIdOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        String name = "name1";
        String desc = "description1";
        var order = Order.of(name, desc);

        order = store.save(order);

        var result =  store.findById(order.getId());

        assertThat(result.getName(), is(name));
        assertThat(result.getDescription(), is(desc));
        assertThat(result.getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindByNameOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        String name = "name1";
        String desc = "description1";
        var order = Order.of(name, desc);

        order = store.save(order);

        var result = (List<Order>) store.findByName(order.getName());

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName(), is(name));
        assertThat(result.get(0).getDescription(), is(desc));
        assertThat(result.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndUpdateOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        String name = "name1";
        String desc = "description1";
        var order = Order.of(name, desc);

        var orderTest = Order.of("test", "test");

        orderTest = store.save(orderTest);

        int i = store.update(order, orderTest.getId());

        assertThat(i, is(1));

        var result =  store.findById(orderTest.getId());

        assertThat(result.getName(), is(name));
        assertThat(result.getDescription(), is(desc));
        assertThat(result.getId(), is(1));
    }
}