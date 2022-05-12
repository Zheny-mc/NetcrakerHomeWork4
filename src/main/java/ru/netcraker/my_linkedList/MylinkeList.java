package ru.netcraker.my_linkedList;

import java.io.Serializable;
import java.util.*;

public class MylinkeList<T> implements Serializable, ILinkedList<T>{

    private static final long serialVersionUID = 8683452581122892300L;

    protected int size = 0;

    /**
     * Измененное количество записей
     */
    protected int modCount = 0;

    protected Node<T> first;
    protected Node<T> last;


    public MylinkeList() {
        first=new Node<>(null,null,null);
        last=new Node<>(first,null,null);
        first.next=last;
        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    @Override
    public void clear() {
        for (Node<T> x = first.next; x != null; ) {
            Node<T> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first=new Node<>(null,null,null);
        last=new Node<>(first,null,null);
        first.next=last;
        size = 0;
        modCount++;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }

    @Override
    public T set(int index, T data) {
        checkElementIndex(index);
        Node<T> x = getNode(index);
        T oldVal = x.data;
        x.data = data;
        return oldVal;
    }

    @Override
    public boolean add(T data) {
        linkLast(data);
        return true;
    }

    @Override
    public void add(int index, T data) {
        checkElementIndex(index);

        if (index == size)
            linkLast(data);
        else
            linkBefore(data, getNode(index));
    }

    @Override
    public boolean remove(T data) {
        if (data == null) {
            for (Node<T> x = first.next; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (data.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        return  unlink(getNode(index));
    }

    @Override
    public int indexOf(T data) {
        int index = 0;
        if (data == null) {
            for (Node<T> x = first.next; x != null; x = x.next) {
                if (x.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = first.next; x != null; x = x.next) {
                if (data.equals(x.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public int lastIndexOf(T data) {
        int index = size;
        if (data == null) {
            for (Node<T> x = last.prev; x != null; x = x.prev) {
                index--;
                if (x.data == null)
                    return index;
            }
        } else {
            for (Node<T> x = last.prev; x != null; x = x.prev) {
                index--;
                if (data.equals(x.data))
                    return index;
            }
        }
        return -1;
    }

    //отсоединить элемент
    T unlink(Node<T> x) {
        x.next.prev=x.prev;
        x.prev.next=x.next;
        size--;
        modCount++;
        return  x.data;
    }

    //вставить элемент до нужнуго элемента
    void linkBefore(T T, Node<T> succ) {
        final Node<T> newNode = new Node<>(succ.prev, T, succ);
        succ.prev.next=newNode;
        succ.prev = newNode;
        size++;
        modCount++;
    }

    //создать и вставить первый элемент
    private void linkFirst(T data) {
        final Node<T> f = first.next;
        final Node<T> newNode = new Node<>(first, data, f);
        f.prev=newNode;
        first.next = newNode;
        size++;
        modCount++;
    }

    //создать и вставить последний элемент
    void linkLast(T data) {
        final Node<T> l = last.prev;
        final Node<T> newNode = new Node<>(l, data, last);
        l.next = newNode;
        last.prev=newNode;
        size++;

        modCount++;
    }

    Node<T> getNode(int index) {
        if (index < (size >> 1)) {
            Node<T> x = first.next;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = last.prev;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;

        int lastRet = -1;

        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor < size();
        }

        public T next() {
            checkForComodification();
            try {
                int i = cursor;
                T next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException T) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                MylinkeList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException T) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;

        for (Node<T> node = first.next; node.next != null; node = node.next) {
            array[index] = node.data;
            index++;
        }

        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        int sz = size;

        T[] array =
                a.length >= sz ?
                        a :
                        (T[]) java.lang.reflect.Array.newInstance(
                                a.getClass().getComponentType(), sz);

        int index = 0;

        for (var node = first.next; node.next != null; node = node.next) {
            array[index++] = (T) node.data;
        }

        return array;
    }

    protected class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("[");

        if (size() > 0) {
            str.append(get(0));
        }
        for (int i = 1; i < size(); i++) {
            str.append(" -> "+ get(i));
        }
        str.append("]");
        return str.toString();
    }
}