package ru.job4j.ood.ocp;
import ru.job4j.ood.srp.*;

import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportInXML implements Report {

    private final Store store;
    private final Marshaller marshaller;

    public ReportInXML(Store store, Marshaller marshaller) {
        this.store = store;
        this.marshaller = marshaller;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();

        try (StringWriter writer = new StringWriter()) {
            for (Employee employee : store.findBy(filter)) {
                marshaller.marshal(employee, writer);
            }
            text.append(writer.getBuffer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
