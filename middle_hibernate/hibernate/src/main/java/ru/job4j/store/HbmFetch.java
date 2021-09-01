package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Candidate;
import ru.job4j.models.HH;
import ru.job4j.models.Vacancy;

import java.util.LinkedList;
import java.util.List;

public class HbmFetch {
    public static void main(String[] args) {
        Candidate candidate = null;

        try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();

            var hh = new HH("DmitriyBankVacancy");

            var vacancy1 = new Vacancy("Java Junior", hh);
            var vacancy2 = new Vacancy("Java middle", hh);

            hh.add(vacancy1);
            hh.add(vacancy2);

            Candidate dmitriy = new Candidate("Dmitriy", 8, 95_000);
            dmitriy.setHh(hh);

            hh.setCandidate(dmitriy);

            session.persist(dmitriy);

            candidate = (Candidate) session.createQuery(
                    "SELECT DISTINCT c "
                    + "FROM Candidate c "
                    + "JOIN FETCH c.hh h "
                    + "JOIN FETCH h.vacancies "
                    + "WHERE c.id = :idC")
                    .setParameter("idC", dmitriy.getId())
                    .uniqueResult();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(candidate);
    }
}
