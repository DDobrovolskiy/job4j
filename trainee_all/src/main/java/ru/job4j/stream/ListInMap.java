package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListInMap {
    public static Map<String, Student> convertToMap(List<Student> students) {
        return students.stream().collect(
                Collectors.toMap(
                    student -> student.getSurname(),
                    student -> student,
                        (oldStudent, newStudent) -> oldStudent
                )
        );
    }
}
