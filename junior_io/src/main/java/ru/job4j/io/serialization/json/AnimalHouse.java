package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        /* JSONArray из ArrayList */
        List<JSONObject> list = new ArrayList<>();
        list.add(new JSONObject("{\"name\":\"Tom\"}"));
        list.add(new JSONObject("{\"name\":\"Jerry\"}"));
        JSONArray jsonAnimals = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Home");
        jsonObject.put("open", "true");
        jsonObject.put("capacity", "2");
        jsonObject.put("animals", jsonAnimals);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        AnimalHouse animalHouse = new AnimalHouse(
                "Home2",
                new Animal("Gerry"),
                new Animal("Spachbob"));

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(animalHouse));
    }
}
