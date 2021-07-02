package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements ListLink<E> {
    private int modCount = 0;
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> nodeNew = new Node<>(last, value, null);
        if (last == null) {
            first = nodeNew;
        } else {
            last.next = nodeNew;
        }
        last = nodeNew;
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        int indexRsl = checkIndex(index);
        int count = 0;
        Node<E> rsl = first;
        while (count < indexRsl) {
            rsl = rsl.next;
            count++;
        }
        return rsl.item;
    }

    private int checkIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }
        return index;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int modCountException = modCount;
            private Node<E> nodeNext = first;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (modCount != modCountException) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> node = nodeNext;
                nodeNext = nodeNext.next;
                cursor++;
                return node.item;
            }
        };
    }

    private class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
