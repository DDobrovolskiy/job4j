package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Candidate;
import ru.job4j.models.Mark;

import java.util.LinkedList;
import java.util.List;

public class HbmCandidates {
    public static void main(String[] args) {
        try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();

            List<Candidate> candidates = new LinkedList<>();

            Candidate dmitriy = new Candidate("Dmitriy", 8, 95_000);
            Candidate pavel = new Candidate("Pavel", 6, 75_000);
            Candidate semen = new Candidate("Semen", 1, 40_000);

            candidates.add(dmitriy);
            candidates.add(pavel);
            candidates.add(semen);

            candidates.forEach(session::save);

            //1 запросы выборки всех кандидатов
            var result1 = session.createQuery("FROM Candidate").list();
            System.out.println(result1);

            //2 кандидата по id
            var result2 = (Candidate) session
                    .createQuery("FROM Candidate c WHERE c.id = :idC")
                    .setParameter("idC", dmitriy.getId())
                    .uniqueResult();
            System.out.println(result2);

            //3 кандидата по имени
            var result3 = (Candidate) session
                    .createQuery("FROM Candidate c WHERE c.name = :nameC")
                    .setParameter("nameC", dmitriy.getName())
                    .list();
            System.out.println(result3);

            //4 обновления записи кандидата
            session.createQuery("UPDATE Candidate c "
                    + "SET c.experience = :experienceC, c.salary = :salaryC "
                    + "WHERE c.id = :idC")
                    .setParameter("experienceC", 7)
                    .setParameter("salaryC", 82000)
                    .setParameter("idC", pavel.getId())
                    .executeUpdate();

            //5 удаления записи кандидата по id.
            session.createQuery("DELETE FROM Candidate c WHERE c.id = :idC")
                    .setParameter("idC", semen.getId())
                    .executeUpdate();

            var resultAll = session.createQuery("FROM Candidate").list();
            System.out.println(resultAll);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
