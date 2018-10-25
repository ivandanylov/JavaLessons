package com.study.lesson22_10.collections;

import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable {
    private static final int INITIAL_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    public void add(T value) {
        System.out.println("Array list size : " + size);
        add(value, size);
    }

    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " should be between 0 and " + size
                    + " (inclusive)");
        }

        if (size == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5) + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;

        size++;
    }

    public T remove(int index) {
        validateIndex(index);

        T result = null;
        result = array[index];

        System.arraycopy(array, index, array, index - 1, size - index - 1);

        return result;
    }

    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " should be between 0 and " + size
                    + " (inclusive)");
        }
    }

    public T set(T value, int index) {
        validateIndex(index);
        T oldValue = array[index];
        array[index] = value;

        return oldValue;
    }

    public void clear() {

        for (T element : array) {
            element = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String result = "[";

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result += array[i];
        }
        return result + "]";
    }

    public boolean isEmpty() {

        return (size == 0);
    }

    public boolean contains(T value) {

        return indexOf(value) != -1;
    }

    public int indexOf(T value) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        //Iterator randomGeneratorIterator = randomGenerator.iterator();
        //while (randomGeneratorIterator.hasNext()) {
        //    Object value = randomGeneratorIterator.next();
        //    System.out.println(value);
        //}

        return -1;
    }

    public int lastIndexOf(T value) {

        for (int i = array.length; i >= 0; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator iterator() {

        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        private int index = -1;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            index++;
            return array[index];
        }

        @Override
        public void remove() {
            ArrayList outer = ArrayList.this;
            outer.remove(index);
        }
    }
}
