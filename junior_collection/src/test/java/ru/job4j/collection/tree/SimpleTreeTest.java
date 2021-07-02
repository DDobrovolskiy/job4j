package ru.job4j.collection.tree;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(4, 5));
        assertTrue(tree.add(5, 6));
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );

        assertFalse(tree.add(10, 6));
        assertFalse(tree.add(1, 6));
        assertFalse(tree.add(1, 1));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(3, 4));
        assertTrue(tree.add(3, 5));
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenIsNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(3, 4));
        assertTrue(tree.add(3, 5));
        assertTrue(tree.add(3, 6));
        assertFalse(tree.isBinary());
    }
}