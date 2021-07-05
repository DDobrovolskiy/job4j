package ru.job4j.ood.lsp.storage;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class ControlQualityTest {

    @Test
    public void whenSortedNormal() {
        //Создаем хранилища
        StoreFood trash = new Trash();
        StoreFood shop = new Shop();
        StoreFood warehouse = new Warehouse();
        /*
          Для того что бы зафиксировать время проверки распределения
          по хранилищам выбираем дату проверки
         */
        LocalDate setToday = LocalDate.of(2021, 7, 1);
        //Наполняем лист операций с привязанными хранилищами
        List<OperationStore> operationStores = new ArrayList<>();
        operationStores.add(new OperationTrash(trash));
        operationStores.add(new OperationShop(shop));
        operationStores.add(new OperationWarehouse(warehouse));
        //Создаем контроллера качества с операциями
        ControlQuality controlQuality = new ControlQuality(operationStores);
        controlQuality.setSortedDay(setToday);

        List<Food> foods = new ArrayList<>();
        foods.add(new FoodDefault("Milk",
                LocalDate.of(2021, 7, 20),
                LocalDate.of(2021, 7, 1),
                69, 0));
        foods.add(new FoodDefault("Bread",
                LocalDate.of(2021, 7, 10),
                LocalDate.of(2021, 6, 1),
                40, 0));
        foods.add(new FoodDefault("Banana",
                LocalDate.of(2021, 10, 29),
                LocalDate.of(2021, 5, 5),
                85, 0));
        foods.add(new FoodDefault("Salt",
                LocalDate.of(2021, 5, 20),
                LocalDate.of(2021, 1, 1),
                185, 0));

        controlQuality.sortedFoodInStore(foods);

        System.out.println("Trash: " + trash.getFoods());
        System.out.println("Shop: " + shop.getFoods());
        System.out.println("Warehouse: " + warehouse.getFoods());

        String actualTrash = "[Food{name='Salt, expiryDate=2021-05-20, "
                + "createDate=2021-01-01, price=185.0, discount=0.0}]";
        Assert.assertEquals(trash.getFoods().toString(), actualTrash);
        String actualShop = "[Food{name='Bread, expiryDate=2021-07-10, createDate=2021-06-01, "
                + "price=40.0, discount=10.0}, Food{name='Banana, expiryDate=2021-10-29, "
                + "createDate=2021-05-05, price=85.0, discount=0.0}]";
        Assert.assertEquals(shop.getFoods().toString(), actualShop);
        String actualWarehouse = "[Food{name='Milk, expiryDate=2021-07-20, "
                + "createDate=2021-07-01, price=69.0, discount=0.0}]";
        Assert.assertEquals(warehouse.getFoods().toString(), actualWarehouse);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenDateWrongCreateAndExpiryAreEqual() {
        //Создаем хранилища
        StoreFood trash = new Trash();
        StoreFood shop = new Shop();
        StoreFood warehouse = new Warehouse();
        /*
          Для того что бы зафиксировать время проверки распределения
          по хранилищам выбираем дату проверки
         */
        LocalDate setToday = LocalDate.of(2021, 7, 1);
        //Наполняем лист операций с привязанными хранилищами
        List<OperationStore> operationStores = new ArrayList<>();
        operationStores.add(new OperationTrash(trash));
        operationStores.add(new OperationShop(shop));
        operationStores.add(new OperationWarehouse(warehouse));
        //Создаем контроллера качества с операциями
        ControlQuality controlQuality = new ControlQuality(operationStores);
        controlQuality.setSortedDay(setToday);

        List<Food> foods = new ArrayList<>();
        foods.add(new FoodDefault("Milk",
                LocalDate.of(2021, 7, 1),
                LocalDate.of(2021, 7, 1),
                69, 0));
        foods.add(new FoodDefault("Bread",
                LocalDate.of(2021, 7, 10),
                LocalDate.of(2021, 6, 1),
                40, 0));
        foods.add(new FoodDefault("Banana",
                LocalDate.of(2021, 10, 29),
                LocalDate.of(2021, 5, 5),
                85, 0));
        foods.add(new FoodDefault("Salt",
                LocalDate.of(2021, 5, 20),
                LocalDate.of(2021, 1, 1),
                185, 0));

        controlQuality.sortedFoodInStore(foods);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenDateWrongCreateAndExpiryMessedUp() {
        //Создаем хранилища
        StoreFood trash = new Trash();
        StoreFood shop = new Shop();
        StoreFood warehouse = new Warehouse();
        /*
          Для того что бы зафиксировать время проверки распределения
          по хранилищам выбираем дату проверки
         */
        LocalDate setToday = LocalDate.of(2021, 7, 1);
        //Наполняем лист операций с привязанными хранилищами
        List<OperationStore> operationStores = new ArrayList<>();
        operationStores.add(new OperationTrash(trash));
        operationStores.add(new OperationShop(shop));
        operationStores.add(new OperationWarehouse(warehouse));
        //Создаем контроллера качества с операциями
        ControlQuality controlQuality = new ControlQuality(operationStores);
        controlQuality.setSortedDay(setToday);

        List<Food> foods = new ArrayList<>();
        foods.add(new FoodDefault("Milk",
                LocalDate.of(2021, 7, 1),
                LocalDate.of(2021, 10, 1),
                69, 0));
        foods.add(new FoodDefault("Bread",
                LocalDate.of(2021, 7, 10),
                LocalDate.of(2021, 6, 1),
                40, 0));
        foods.add(new FoodDefault("Banana",
                LocalDate.of(2021, 10, 29),
                LocalDate.of(2021, 5, 5),
                85, 0));
        foods.add(new FoodDefault("Salt",
                LocalDate.of(2021, 5, 20),
                LocalDate.of(2021, 1, 1),
                185, 0));

        controlQuality.sortedFoodInStore(foods);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenFoodNotDistributed() {
        //Создаем хранилища
        StoreFood trash = new Trash();
        StoreFood shop = new Shop();
        //StoreFood warehouse = new Warehouse();
        /*
          Для того что бы зафиксировать время проверки распределения
          по хранилищам выбираем дату проверки
         */
        LocalDate setToday = LocalDate.of(2021, 7, 1);
        //Наполняем лист операций с привязанными хранилищами
        List<OperationStore> operationStores = new ArrayList<>();
        operationStores.add(new OperationTrash(trash));
        operationStores.add(new OperationShop(shop));
        //operationStores.add(new OperationWarehouse(warehouse).setToDay(setToday));
        //Создаем контроллера качества с операциями
        ControlQuality controlQuality = new ControlQuality(operationStores);
        controlQuality.setSortedDay(setToday);

        List<Food> foods = new ArrayList<>();
        foods.add(new FoodDefault("Milk",
                LocalDate.of(2021, 7, 1),
                LocalDate.of(2021, 10, 1),
                69, 0));
        foods.add(new FoodDefault("Bread",
                LocalDate.of(2021, 7, 10),
                LocalDate.of(2021, 6, 1),
                40, 0));
        foods.add(new FoodDefault("Banana",
                LocalDate.of(2021, 10, 29),
                LocalDate.of(2021, 5, 5),
                85, 0));
        foods.add(new FoodDefault("Salt",
                LocalDate.of(2021, 5, 20),
                LocalDate.of(2021, 1, 1),
                185, 0));

        controlQuality.sortedFoodInStore(foods);
    }

    @Test
    public void whenResortedNormal() {
        //Создаем хранилища
        StoreFood trash = new Trash();
        StoreFood shop = new Shop();
        StoreFood warehouse = new Warehouse();
        /*
          Для того что бы зафиксировать время проверки распределения
          по хранилищам выбираем дату проверки
         */
        LocalDate setToday = LocalDate.of(2021, 7, 1);

        OperationStore operationTrash = new OperationTrash(trash);
        OperationStore operationShop = new OperationShop(shop);
        OperationStore operationWarehouse = new OperationWarehouse(warehouse);

        //Наполняем лист операций с привязанными хранилищами
        List<OperationStore> operationStores = new ArrayList<>();
        operationStores.add(operationTrash);
        operationStores.add(operationShop);
        operationStores.add(operationWarehouse);

        //Создаем контроллера качества с операциями
        ControlQuality controlQuality = new ControlQuality(operationStores);
        controlQuality.setSortedDay(setToday);

        List<Food> foods = new ArrayList<>();
        foods.add(new FoodDefault("Milk",
                LocalDate.of(2021, 7, 20),
                LocalDate.of(2021, 7, 1),
                69, 0));
        foods.add(new FoodDefault("Bread",
                LocalDate.of(2021, 7, 10),
                LocalDate.of(2021, 6, 1),
                40, 0));
        foods.add(new FoodDefault("Banana",
                LocalDate.of(2021, 10, 29),
                LocalDate.of(2021, 5, 5),
                85, 0));
        foods.add(new FoodDefault("Salt",
                LocalDate.of(2021, 5, 20),
                LocalDate.of(2021, 1, 1),
                185, 0));

        controlQuality.sortedFoodInStore(foods);

        System.out.println("Trash: " + trash.getFoods());
        System.out.println("Shop: " + shop.getFoods());
        System.out.println("Warehouse: " + warehouse.getFoods());

        Assert.assertThat(1, is(trash.getFoods().size()));
        Assert.assertThat(2, is(shop.getFoods().size()));
        Assert.assertThat(1, is(warehouse.getFoods().size()));

        LocalDate setTodayNew = LocalDate.of(2023, 7, 1);
        controlQuality.setSortedDay(setTodayNew);
        controlQuality.resort();

        System.out.println("Trash: " + trash.getFoods());
        System.out.println("Shop: " + shop.getFoods());
        System.out.println("Warehouse: " + warehouse.getFoods());

        Assert.assertThat(4, is(trash.getFoods().size()));
        Assert.assertThat(0, is(shop.getFoods().size()));
        Assert.assertThat(0, is(warehouse.getFoods().size()));
    }
}