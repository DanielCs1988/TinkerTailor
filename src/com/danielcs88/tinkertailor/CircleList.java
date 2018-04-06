package com.danielcs88.tinkertailor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CircleList <E> implements List<E> {

    private int size;
    private Node<E> top;
    private Node<E> bottom;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircleIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E elem) {
        Node<E> newNode = new Node<>(elem);
        if (top == null) {
            bottom = newNode;
        } else {
            newNode.setNext(top);
            top.setPrev(newNode);
            if (top.getNext() == null) {
                newNode.setPrev(top);
                top.setNext(newNode);
            } else {
                newNode.setPrev(bottom);
                bottom.setNext(newNode);
            }
        }
        top = newNode;
        size ++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) return false;
        int index = 0;
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            if (iter.next() == o) {
                iter.remove();
                return true;
            }
            if (index++ == size) break;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private Iterator<E> roll(int index) {
        Iterator<E> iter = iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter;
    }

    @Override
    public E get(int index) {
        if (size == 0) return null;
        Iterator<E> iter = roll(index);
        return iter.next();
    }

    @Override
    public E set(int index, E element) {
        if (size == 0) return null;
        Iterator<E> iter = roll(index);
        ((CircleIterator)iter).set(element);
        return element;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        if (size == 0) return null;
        Iterator<E> iter = roll(index);
        iter.remove();
        size --;
        return null; // FIX THIS
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<E> iter = iterator();
        for (int i = 0; i < size; i++) {
            sb.append(iter.next());
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private final class CircleIterator implements Iterator<E> {

        private Node<E> current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (current == null) return null;
            E retVal = current.getElem();
            current = current.getNext();
            return retVal;
        }

        public void set(E elem) {
            current.setElem(elem);
        }

        @Override
        public void remove() {
            size --;
            if (current.getNext() == null) {
                top = null;
                return;
            }
            // MAY THROW ERROR!
            if (current.getNext() == current.getPrev()) {
                top = current.getNext();
                return;
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            if (current == top) {top = current.getNext();}
            if (current == bottom) {bottom = current.getPrev();}
            current = current.getPrev();
        }
    }
}