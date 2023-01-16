/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.hash.hash_exercise_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class OpenAdressingHashTable<K,V> implements Dictionary<K, V> {
      public static final int DEFAULT_INITIAL_SIZE = 17;
    private Entry[] array;
    private int size;
    private int k=1; //k will be the value changing to the number of times rehashing was needed, it will be used in order for h(x) to be different after each time rehashing was done
    private ArrayList<Integer> h= new ArrayList<Integer>();

    public OpenAdressingHashTable(int m) {
        if (m <= 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        this.size = 0;
         array =new Entry[m];
        for (int i = 0; i < m; i++) {
            this.array[i] = null;
        }
    }


    public OpenAdressingHashTable() {
        this(DEFAULT_INITIAL_SIZE);
    }

    @Override
    public void put(K key, V value) {
        ReHashIfNeeded();
        insert(key, value);

    }
    public int hashFuction(K key){
        return (k*5*Math.abs(key.hashCode()) )% array.length;
    }

    @Override
    public V remove(K key) {
        // Apply hash function to find index for given key
        int hashIndex = hashFuction(key);
       // find next free space
        while (array[hashIndex] !=null) {
                  //  if(key.equals(array[hashIndex].getKey())){
                    array[hashIndex] = array[hashIndex+1];
            hashIndex++;
            hashIndex %= array.length;
     //   }
        // Apply hash function
        // to find index for given key
        
        
        
                  
            }
            size--;
        
        
        // If not found return null
        return null;
    }
   
    @Override
    public V get(K key) {
          // Apply hash function to find index for given key
        int hashIndex = hashFuction(key);
        int counter = 0;
 
        // finding the node with given key
        while (array[hashIndex] != null) { // int counter =0; // BUG!
                   counter++;
            if (counter > array.length) // to avoid infinite loop
                return null;
 
            // if node found return its value
            if (key.equals(array[hashIndex].getKey()))
                return (V) array[hashIndex].getValue();
            hashIndex++;
            hashIndex %= array.length;
        }
 
        // If not found return null
        return null;
    }

    @Override
    public boolean contains(K key) {
         // Apply hash function to find index for given key
        int hashIndex = hashFuction(key);
        int counter = 0;
 
        // finding the node with given key
        while (array[hashIndex] != null) { // int counter =0; // BUG!
 
            if (counter++ > array.length) // to avoid infinite loop
                return false;
 
            // if node found return true
            if (key.equals(array[hashIndex].getKey()))
                return true;
            hashIndex++;
            hashIndex %= array.length;
        }
 
        // If not found return null
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
            array[i]=null;
        }

    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashIterator();
    }

    

    private void insert(K key, V value) {
        EntryImpl<K, V> temp =new EntryImpl<K,V>(key,value);             
        // Apply hash function to find index for given key
        int hashIndex = hashFuction(key);
       // find next free space
        while (array[hashIndex] !=null) {
                  //  if(key.equals(array[hashIndex].getKey())){
            hashIndex++;
            hashIndex %= array.length;
     //   }
                }
 
        // if new node to be inserted
        // increase the current size
        if (array[hashIndex] == null)          
            size++;
        array[hashIndex] = temp;
    }
 
    

    private void ReHashIfNeeded() {
        double avg = (double) size / array.length;
        int newLength;
        if (avg > 4) {
            newLength = array.length * 2;
            k=k+1;
        } else if (avg < (1 / 4) && array.length > 2 * DEFAULT_INITIAL_SIZE) {
            newLength = array.length / 2;
            k=k+1;
        } else {
            return;
        }
        OpenAdressingHashTable<K, V> newHashTable = new OpenAdressingHashTable<K, V>(newLength);
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
 //i m not going to use this implementation at all
    private class HashIterator implements Iterator<Entry<K, V>> {

        private int i;
        private Iterator<Entry<K, V>> it;

        public HashIterator() {
            
        }

        @Override
        public boolean hasNext() {
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
    
