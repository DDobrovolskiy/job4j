package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int countMod = 0;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
        countMod++;
        size++;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        countMod++;
        size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> delete = head;
        head = head.next;
        countMod++;
        size--;
        return delete.value;
    }

    public boolean revert() {
        if (!isEmpty() && head.next != null) {
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
                Node<T> currentNext = current.next;
                current.next = head;
                head = current;
                current = currentNext;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = head;
            private int countModExpected = countMod;

            @Override
            public boolean hasNext() {
                if (countMod != countModExpected) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
