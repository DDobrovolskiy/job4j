package ru.job4j.ood.srp;


import java.util.function.Predicate;

public class ReportProgrammer implements Report {

    private Store store;

    public ReportProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<Name>").append(employee.getName()).append("</Name>")
                    .append(System.lineSeparator())
                    .append("<Hired>").append(employee.getHired()).append("</Hired>")
                    .append(System.lineSeparator())
                    .append("<Fired>").append(employee.getFired()).append("</Fired>")
                    .append(System.lineSeparator())
                    .append("<Salary>").append(employee.getSalary()).append("</Salary>")
                    .append(System.lineSeparator());
        }
        text.append("<\\html>");
        return text.toString();
    }
}
