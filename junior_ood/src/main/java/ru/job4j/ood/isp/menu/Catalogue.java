package ru.job4j.ood.isp.menu;

import java.util.List;

public interface Catalogue {
    Catalogue createChildCatalogue(String task);

    String printCatalogue();
}
