package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Dmitriy Dobrovolskiy");
        student.setGroup("PS-502");
        student.setEnrollment(new Date());
        System.out.println("Student: " + student.getName()
                + "in group " + student.getGroup()
                + " enrollment: " + student.getEnrollment());
    }
}
