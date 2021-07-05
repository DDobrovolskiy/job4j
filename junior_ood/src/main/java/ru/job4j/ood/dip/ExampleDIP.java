package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExampleDIP {
    //В большом логическом класс поле не зависит от абстракции
    public static class Example1 {
        private static class BigClassLogic {
            private List<Integer> foo = new LinkedList<>();
        }
    }

    // Метод принимает не абстракцию
    public static class Example2 {
        private static class Class {
            public void foo(ArrayList<Integer> arrayList) {

            }
        }
    }

    //Метод возращает не абстракцию
    public static class Example3 {
        private static class Class {
            public ArrayList<Integer> foo() {
                return new ArrayList<>();
            }
        }
    }
}
