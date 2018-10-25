package com.study.lesson22_10.collections;

public interface List<T> {
    void add(T value);
    void add(T value, int index);
    T remove(int index);
    T get(int index);
    T set(T value, int index);
    void clear();
    int size();
    boolean isEmpty();
    boolean contains(T value);
    int indexOf(T value);
    int lastIndexOf(T value);
}
