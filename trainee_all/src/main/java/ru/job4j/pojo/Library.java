package ru.job4j.pojo;

public class Library {

    private static Book[] library;

    public static void main(String[] args) {
        library = new Book[4];
        library[0] = new Book("Clean code", 100);
        library[1] = new Book("Engineer profession", 350);
        library[2] = new Book("Java for dummy", 100500);
        library[3] = new Book("Garry Potter", 350);
        for (int i = 0; i < library.length; i++) {
            print(i);
        }
        System.out.println();
        Book changeBook = library[0];
        library[0] = library[3];
        library[3] = changeBook;
        for (int i = 0; i < library.length; i++) {
            print(i);
        }
        System.out.println();
        for (int i = 0; i < library.length; i++) {
            if (library[i].getName().equals("Clean code")) {
                print(i);
            }
        }
    }

    private static void print(int indexBook) {
        System.out.println("Book name: " + library[indexBook].getName()
                + ". Pages :" + library[indexBook].getPages());
    }
}
