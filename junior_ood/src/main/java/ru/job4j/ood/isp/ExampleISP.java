package ru.job4j.ood.isp;

import ru.job4j.ood.lsp.ExampleLSP;

public class ExampleISP {

    public static class Example1 {
        public interface VeryBigInterface {
            void foo1();

            void foo2();

            //foo3, foo4, foo5, ...
            void foo52();
        }

        //Классы используют только части этого интефейса
        private static class Class1 {
            public void foo(VeryBigInterface veryBigInterface) {
                veryBigInterface.foo1();
                veryBigInterface.foo2();
            }
        }

        //Классы используют только части этого интефейса
        private static class Class2 {
            public void foo(VeryBigInterface veryBigInterface) {
                veryBigInterface.foo52();
            }
        }
    }

    public static class Example2 {
        public interface VeryBigInterface {
            void foo1();

            void foo2();

            //foo3, foo4, foo5, ...
            void foo52();
        }

        //Классы реализуют только чать большого интефеса
        private static class Class1 implements VeryBigInterface {

            @Override
            public void foo1() {
                //Logic
            }

            @Override
            public void foo2() {
                //пустой
            }

            @Override
            public void foo52() {
                //пустой
            }
        }
    }

    //В интерфейсе присутствуют совершенно разные по логике методы
    public static class Example3 {
        public interface DisplacedInterface {
            void connectDataBase();

            void printHelloWorld();
        }
    }
}
