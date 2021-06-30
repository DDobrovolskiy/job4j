package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class ReportEconomic implements Report {

    private Store store;
    private String format;

    public ReportEconomic(Store store, String format) {
        this.format = format;
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(String.format(format, employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
