package ru.job4j.ood.isp.menu;

public class Content {
    public static void main(String[] args) {
        Catalogue root = new Paragraph("Root");
        Catalogue first = root.createChildCatalogue("First");
        first.createChildCatalogue("First-first");
        first.createChildCatalogue("First-second").createChildCatalogue("First-second-first");
        root.createChildCatalogue("Second").createChildCatalogue("Second-first");

        System.out.println("Content:");
        System.out.println(root.printCatalogue());
        System.out.println(((Operation) first).getTask());
    }
}
