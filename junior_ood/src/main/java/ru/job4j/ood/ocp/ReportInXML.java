package ru.job4j.ood.ocp;
import ru.job4j.ood.srp.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportInXML implements Report {

    private final Store store;

    public ReportInXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(EmployeeStore.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new EmployeeStore(store.findBy(filter)), writer);
            text.append(writer.getBuffer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
