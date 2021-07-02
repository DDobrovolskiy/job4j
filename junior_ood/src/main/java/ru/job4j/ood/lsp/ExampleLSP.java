package ru.job4j.ood.lsp;

public class ExampleLSP {
    //Отсутствие проверки на валидность методов потомка
    public static class Example1 {
        private static class Parent {
            public int decrement(int i) {
                if (i == 0) {
                    throw new IllegalArgumentException();
                }
                return 10 / i;
            }
        }

        private static class Children extends Parent {

            @Override
            public int decrement(int i) {
                return 100 / i;
            }
        }
    }

    // Усиления условия в потомке
    public static class Example2 {
        private static class Parent {
            private String name;

            public Parent(String name) {
                this.name = name;
            }
        }

        private static class Children extends Parent {

            public Children(String name) {
                super(name);
                if (name.length() < 5) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    //Ослабление условия в потомке
    public static class Example3 {
        private static class Class1 {
            public void print(String message) {
                if (message == null) {
                    throw new IllegalArgumentException();
                }
                System.out.println(message);
            }
        }

        private static class Class2 extends Class1 {
            @Override
            public void print(String message) {
                System.out.println(message);
            }
        }

    }
}
