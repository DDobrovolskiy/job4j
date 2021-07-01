package ru.job4j.ood.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;
import java.util.Comparator;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        System.out.println(expect);
        System.out.println(engine.generate(em -> true));
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportProgrammer(store); //
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(System.lineSeparator())
                .append("<Name>").append(worker.getName()).append("</Name>")
                .append(System.lineSeparator())
                .append("<Hired>").append(worker.getHired()).append("</Hired>")
                .append(System.lineSeparator())
                .append("<Fired>").append(worker.getFired()).append("</Fired>")
                .append(System.lineSeparator())
                .append("<Salary>").append(worker.getSalary()).append("</Salary>")
                .append(System.lineSeparator())
                .append("<\\html>");
        System.out.println(expect);
        System.out.println(engine.generate(em -> true));
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenEconomicGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEconomic(store, "%.2f"); //
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(String.format("%.2f", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        System.out.println(expect);
        System.out.println(engine.generate(em -> true));
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Dima", now, now, 150);
        store.add(worker);
        store.add(worker1);
        Report engine = new ReportHR(store, (o1, o2) ->
                Double.compare(o2.getSalary(), o1.getSalary()));
        StringBuilder expect = new StringBuilder()
                .append("Name;Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        System.out.println(expect);
        System.out.println(engine.generate(em -> true));
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}