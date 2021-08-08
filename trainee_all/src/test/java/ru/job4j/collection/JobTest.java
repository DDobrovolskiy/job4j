package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class JobTest {

    @Test
    public void whenCompatorByNameAndProrityNameClone() {
        Comparator<Job> cmpNamePriority = new SortJobByName().thenComparing(
                new SortJobByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("A", 1),
                new Job("A", 0)
        );
        TreeSet<Job> treeSet = new TreeSet(cmpNamePriority);
        treeSet.add(new Job("A", 0));
        treeSet.add(new Job("A", 1));
        System.out.println(treeSet);
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameAndProrityName() {
        Comparator<Job> cmpNamePriority = new SortJobByName().thenComparing(
                new SortJobByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("A", 1),
                new Job("B", 0)
        );
        TreeSet<Job> treeSet = new TreeSet(cmpNamePriority);
        treeSet.add(new Job("A", 1));
        treeSet.add(new Job("B", 0));
        System.out.println(treeSet);
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameAndProrityNameCloneReverse() {
        Comparator<Job> cmpNamePriority = new SortByNameReverse().thenComparing(
                new SortJobByPriorityReverse());
        int rsl = cmpNamePriority.compare(
                new Job("A", 0),
                new Job("A", 1)
        );
        TreeSet<Job> treeSet = new TreeSet(cmpNamePriority);
        treeSet.add(new Job("A", 0));
        treeSet.add(new Job("A", 1));
        System.out.println(treeSet);
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameAndProrityNameReverse() {
        Comparator<Job> cmpNamePriority = new SortByNameReverse().thenComparing(
                new SortJobByPriorityReverse());
        int rsl = cmpNamePriority.compare(
                new Job("B", 0),
                new Job("A", 1)
        );
        TreeSet<Job> treeSet = new TreeSet(cmpNamePriority);
        treeSet.add(new Job("A", 1));
        treeSet.add(new Job("B", 0));
        System.out.println(treeSet);
        assertThat(rsl, lessThan(0));
    }
}