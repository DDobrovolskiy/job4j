package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Author;
import ru.job4j.models.Book;
import ru.job4j.models.Car;
import ru.job4j.models.Mark;

import java.util.LinkedList;
import java.util.List;

public class HbmBookRun {
    public static void main(String[] args) {

        try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();

            Book book1 = Book.of("War and Piece");
            Book book2 = Book.of("Java part 1");
            Book book3 = Book.of("Java part 2");

            Author author1 = Author.of("Dmitriy");
            Author author2 = Author.of("Sergey");
            Author author3 = Author.of("Leo");

            author1.addBook(book1);
            author1.addBook(book2);
            author1.addBook(book3);

            author2.addBook(book2);
            author2.addBook(book3);

            author3.addBook(book1);

            session.persist(author1);
            session.persist(author2);
            session.persist(author3);

            session.remove(author2);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
