package ru.job4j.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
//   public ArrayList<Person> find(String key) {
//       ArrayList<Person> result = new ArrayList<>();
//       for (Person person : persons) {
//           if (person.getName().contains(key)
//                   || person.getSurname().contains(key)
//                   || person.getPhone().contains(key)
//                   || person.getAddress().contains(key)) {
//               result.add(person);
//           }
//           }
//       return result;
//   }

    public ArrayList<Person> find(String key) {
        Predicate<Person> findInName = person -> person.getName().contains(key);
        Predicate<Person> findInSurname = person -> person.getSurname().contains(key);
        Predicate<Person> findInPhone = person -> person.getPhone().contains(key);
        Predicate<Person> findInAddress = person -> person.getAddress().contains(key);
        Predicate<Person> combine = findInName.or(findInSurname.or(findInPhone.or(findInAddress)));
        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
