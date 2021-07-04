package ru.job4j.ood.isp.menu;

import java.util.LinkedList;
import java.util.List;

public class Paragraph implements Catalogue, Operation {
    private int item;
    private final String task;
    private Paragraph parent;
    private final List<Paragraph> children = new LinkedList<>();

    public Paragraph(String task) {
        this.task = task;
    }

    @Override
    public Catalogue createChildCatalogue(String task) {
        Paragraph paragraph = new Paragraph(task);
        paragraph.parent = this;
        paragraph.item = children.size() + 1;
        children.add(paragraph);
        return paragraph;
    }

    private boolean isRoot() {
        return parent == null;
    }

    private String getIndex() {
        String prefixParent = "";
        if (!isRoot()) {
            prefixParent = parent.getIndex() + item + ".";
        }
        return prefixParent;
    }

    private String getIndent() {
        String indent = "";
        if (!isRoot()) {
            if (!parent.isRoot()) {
                indent = parent.getIndent() + "---";
            }
        }
        return indent;
    }

    @Override
    public String printCatalogue() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!isRoot()) {
            stringBuilder
                    .append(getIndent())
                    .append("Задача ")
                    .append(getIndex())
                    .append(" ")
                    .append(task)
                    .append(System.lineSeparator());
        }
        children.forEach(paragraph -> {
            stringBuilder.append(paragraph.printCatalogue());
        });
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Задача " + getIndex() + " " + task;
    }

    @Override
    public String getTask() {
        return task;
    }
}
