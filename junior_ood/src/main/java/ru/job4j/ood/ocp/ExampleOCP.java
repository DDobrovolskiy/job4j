package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.Scanner;

public class ExampleOCP {
    //Объявлена конкретная структура, не всегда правильно, лучше воспользоваться интерфейсом
    public static class Example1 {
        ArrayList<Integer> arrayList;

        public Example1(ArrayList<Integer> arrayList) {
            this.arrayList = arrayList;
        }
    }

    //Отсутствие интерфейса может привести к сложностям расширения
    public static class Example2 {
        public void printFoo() {
            System.out.println("Foo...");
        }
    }

    //Невозможно поменять истояник информации без изменений когда
    public static class Example3 {
        public void printAnswer() {
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.nextLine());
        }
    }
}
