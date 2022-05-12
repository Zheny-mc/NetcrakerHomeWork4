package ru.netcraker.my_linkedList;

public interface ILinkedList<T> extends Iterable<T> {

    boolean add(T data);
    void add(int index, T data);
    void clear();
    T get(int index);
    int indexOf(T data);
    T remove(int index);
    boolean remove(T data);
    T set(int index, T data);
    int size();
    Object[] toArray();
    <T> T[] toArray(T[] a);
    String toString();

    boolean isEmpty();
    boolean contains(T data);
}