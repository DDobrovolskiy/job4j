package ru.job4j.ood.srp;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ExampleSRP {
    //Абсолютно разные работы методов вывода информации и времени
    public interface Example1 {
        //Метод выводящий информацию
        void print(String message);

        //Метод возвращающий время
        LocalDateTime getTime();
    }

    //Класс знает как создавать объект
    public static class Example2 {
        public void printAnswer() {
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.nextLine());
        }
    }

    //Два интерфейса разделяют схожую работу
    public interface Example3 {
        void foo1();
    }

    public interface Example4 {
        void foo2();
    }
}
