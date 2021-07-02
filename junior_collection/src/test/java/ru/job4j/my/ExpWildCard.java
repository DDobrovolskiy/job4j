package ru.job4j.my;

import java.util.ArrayList;
import java.util.List;

public class ExpWildCard {
    public static <T> void copyOf(List<? extends T> src, List<? super T> desc) {
        desc.addAll(src);
    }

    public static void main(String[] args) {
        List<Animal> desc = new ArrayList<>();
        List<Dog> srcDog = List.of(new Dog());
        List<Cat> srcCat = List.of(new Cat());
        copyOf(srcCat, desc);
        copyOf(srcDog, desc);
        desc.forEach(x -> x.sound());
    }

    static class Animal {
        private String sound = "null";

        public void setSound(String sound) {
            this.sound = sound;
        }

        public void sound() {
            System.out.println(sound);
        }
    }

    static class Dog extends Animal {
        public Dog() {
            setSound("Bark");
        }
    }

    static class Cat extends Animal {
        public Cat() {
            setSound("Mew");
        }
    }
}
