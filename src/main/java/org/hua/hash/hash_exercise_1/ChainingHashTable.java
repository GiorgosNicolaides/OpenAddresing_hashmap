package org.hua.hash.hash_exercise_1;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ChainingHashTable<K, V> implements Dictionary<K, V> {

    public static final int DEFAULT_INITIAL_SIZE = 17;
    private LinkedList<Entry<K, V>>[] array;
    private int size;
    private ArrayList<Integer> h= new ArrayList<Integer>();

    public ChainingHashTable(int m) {
        if (m <= 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        this.size = 0;
        this.array = (LinkedList<Entry<K, V>>[]) Array.newInstance(LinkedList.class, m);
        for (int i = 0; i < m; i++) {
            this.array[i] = new LinkedList<>();
        }
    }

    public ChainingHashTable() {
        this(DEFAULT_INITIAL_SIZE);
    }

    @Override
    public void put(K key, V value) {
        ReHashIfNeeded();
        insert(key, value);

    }

    @Override
    public V remove(K key) {
        LinkedList<Entry<K, V>> List = getList(key);
        ListIterator<Entry<K, V>> it = List.listIterator();
        while (it.hasNext()) {
            Entry<K, V> curr = it.next();
            if (key.equals(curr.getKey())) {
                V value = curr.getValue();
                it.remove();
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        LinkedList<Entry<K, V>> List = getList(key);
        for (Entry<K, V> e : List) {
            if (key.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        LinkedList<Entry<K, V>> List = getList(key);
        Iterator<Entry<K, V>> it = List.iterator();

        while (it.hasNext()) {
            Entry<K, V> curr = it.next();
            if (key.equals(curr.getKey())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < array.length; i++) {
            array[i].clear();
        }

    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashIterator();
    }

    private LinkedList<Entry<K, V>> getList(K key) {
        return array[Math.abs(key.hashCode()) % array.length];

    }

    private void insert(K key, V value) {
        LinkedList<Entry<K, V>> List = getList(key);
        ListIterator<Entry<K, V>> it = List.listIterator();
        while (it.hasNext()) {
            Entry<K, V> curr = it.next();
            if (key.equals(curr.getKey())) {
                it.set(new EntryImpl<>(key, value));

                return;
            }
        }
        List.addFirst(new EntryImpl<>(key, value));
        size++;
    }

    private void ReHashIfNeeded() {
        double avg = (double) size / array.length;
        int newLength;
        if (avg > 4) {
            newLength = array.length * 2;
        } else if (avg < (1 / 4) && array.length > 2 * DEFAULT_INITIAL_SIZE) {
            newLength = array.length / 2;
        } else {
            return;
        }
        ChainingHashTable<K, V> newHashTable = new ChainingHashTable<K, V>(newLength);
        for (Entry<K, V> e : this) {
            newHashTable.insert(e.getKey(), e.getValue());
        }
        this.array = newHashTable.array;
        this.size = newHashTable.size;
    }

    private static class EntryImpl<K, V> implements Entry<K, V> {

        private K key;
        private V value;

        public EntryImpl(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

    }

    private class HashIterator implements Iterator<Entry<K, V>> {

        private int i;
        private Iterator<Entry<K, V>> it;

        public HashIterator() {
            i = 0;
            it = array[0].iterator();
        }

        @Override
        public boolean hasNext() {
            if (it.hasNext()) {
                return true;
            }
            while (i < array.length - 1) {
                i++;
                it = array[i].iterator();
                if (it.hasNext()) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return it.next();
        }

    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

}