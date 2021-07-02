package ru.job4j.my;

import java.lang.reflect.ParameterizedType;

public class ExpParamType<T> {

    public static void main(String[] args) {
        ExpParamType<String> expParamType = new ExpParamType<>();
    }

}

class SuperClass extends ExpParamType<String> {

    private <T> void test() {
        Class<T> t = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(t);
    }

    public static void main(String[] args) {
        SuperClass superClass = new SuperClass();
        superClass.test();
    }
}
