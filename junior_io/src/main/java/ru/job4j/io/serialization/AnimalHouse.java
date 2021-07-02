package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class AnimalHouse {
    private String name;
    private boolean open;
    private int capacity;
    private Animal[] animals;

    public AnimalHouse() {
    }

    public AnimalHouse(String name, Animal... animals) {
        this.open = true;
        this.capacity = animals.length;
        this.name = name;
        this.animals = animals;
    }

    public Animal getFirst() {
        return animals[0];
    }

    public boolean isOpen() {
        return open;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AnimalHouse{"
                + "name='" + name + '\''
                + ", open=" + open
                + ", capacity=" + capacity
                + ", animals=" + Arrays.toString(animals)
                + '}';
    }

    public static void main(String[] args) {
        final AnimalHouse house1 = new AnimalHouse("Home1", new Animal("Jerry"), new Animal("Tom"));

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(house1));

        /* Модифицируем json-строку */
        final String houseString =
                "{"
                        + "\"name\":Home2,"
                        + "\"open\":false,"
                        + "\"capacity\":1,"
                        + "\"animals\":"
                        + "["
                        + "{"
                        + "\"name\":\"Gerry\""
                        + "}"
                        + "]"
                        + "}";
        final AnimalHouse house2 = gson.fromJson(houseString, AnimalHouse.class);
        System.out.println(house2);
    }

}
