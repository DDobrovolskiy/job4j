package ru.job4j.ood.ocp;

import com.google.gson.Gson;
import ru.job4j.ood.srp.*;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private final Store store;
    private final Gson gson;

    public ReportJSON(Store store, Gson gson) {
        this.store = store;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append(gson.toJson(employee))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
