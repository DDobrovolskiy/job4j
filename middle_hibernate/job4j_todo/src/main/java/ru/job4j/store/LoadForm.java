package ru.job4j.store;

import ru.job4j.models.Category;

import java.util.ArrayList;
import java.util.List;

public class LoadForm {
    public static void main(String[] args) {
        Store store = new HibernateStore();
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Проект JavaJob"));
        categories.add(new Category("Рабочие вопросы"));
        categories.add(new Category("Домашние дела"));
        categories.add(new Category("Срочно"));
        categories.add(new Category("Личное"));

        categories.forEach(store::addCategory);

        List<Category> categoryList = store.findAllCategories();
        System.out.println(categoryList);
    }
}
