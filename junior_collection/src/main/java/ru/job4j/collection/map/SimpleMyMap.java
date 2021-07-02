package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMyMap<K, V> implements Iterable<SimpleMyMap.Node<K, V>> {
    private static final float LOAD_FACTOR = 0.75F;
    private int size = 0;
    private int countMod = 0;
    private Node<K, V>[] data;

    public SimpleMyMap() {
        data = new Node[16];
    }

    public boolean insert(K key, V value) {
        if (insert(key, value, data)) {
            size++;
            changeSize();
            countMod++;
            return true;
        }
        return false;
    }

    private boolean insert(K key, V value, Node<K, V>[] nodes) {
        int index = index(key, nodes);
        boolean flag = false;
        if (nodes[index] == null) {
            nodes[index] = new Node<>(key, value);
            flag = true;
        } else if (isSame(nodes[index].key, key)) {
            nodes[index].value = value;
            flag = true;
        }
        return flag;
    }

    public V get(K key) {
        int index = index(key, data);
        if (data[index] == null) {
            return null;
        } else if (isSame(data[index].key, key)) {
            return data[index].value;
        }
        return null;
    }

    public boolean delete(K key) {
        int index = index(key, data);
        if (data[index] == null)  {
            return false;
        } else if (isSame(data[index].key, key)) {
            data[index] = null;
            size--;
            countMod++;
            return true;
        }
        return false;
    }

    private int index(K key, Node<K, V>[] nodes) {
        return hash(key) & (nodes.length - 1);
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private void changeSize() {
        if (size > data.length * LOAD_FACTOR) {
            Node<K, V>[] newData = new Node[data.length * 2];
            for (Node<K, V> node : data) {
                if (node != null) {
                    insert(node.key, node.value, newData);
                }
            }
            data = newData;
        }
    }

    private boolean isSame(K key1, K key2) {
        return hash(key1) == hash(key2) && (key1 == key2 || Objects.equals(key1, key2));
    }

    static class Node<Key, Value> {
        private final Key key;
        private Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }
    }

    @Override
    public Iterator<SimpleMyMap.Node<K, V>> iterator() {
        return new Iterator<>() {

            private final int countModException = countMod;
            private int cursor = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (countMod != countModException) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (data[index] == null) {
                    index++;
                }
                cursor++;
                return data[index++];
            }
        };
    }
}
