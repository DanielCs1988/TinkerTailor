package com.danielcs88.tinkertailor;

import java.util.*;
import java.util.function.Consumer;

public class CircleList <E> {

    private int size;
    private Node<E> middleNode;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<E> iterator() {
        return new CircleIterator();
    }

    public boolean add(E elem) {
        Node<E> newNode = new Node<>(elem);
        if (middleNode != null) {
            newNode.next = middleNode;
            if (middleNode.next != null) {
                newNode.prev = middleNode.prev;
            } else {
                newNode.prev = middleNode;
                middleNode.next = newNode;
            }
            middleNode.prev = newNode;
        }
        middleNode = newNode;
        size ++;
        return true;
    }

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

    public void clear() {
        for (Node<E> node = middleNode; node != null;) {
            Node<E> next = node.next;
            node.elem = null;
            node.next = null;
            node.prev = null;
            node = next;
        }
        size = 0;
        middleNode = null;
    }

    public void forEach(Consumer<? super E> action) {
        Node<E> currentElement = middleNode;
        for (int i = 0; i < size; i++) {
            action.accept(currentElement.elem);
            currentElement = currentElement.next;
        }
    }

    private CircleIterator setIteratorToIndex(int index) {
        CircleIterator iter = new CircleIterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter;
    }

    public E get(int index) {
        if (size == 0) return null;
        Iterator<E> iter = setIteratorToIndex(index);
        return iter.next();
    }

    public E set(int index, E element) {
        if (size == 0) return null;
        Iterator<E> iter = setIteratorToIndex(index);
        ((CircleIterator)iter).set(element);
        return element;
    }

    public void add(int index, E element) {
        // TODO
    }

    public E remove(int index) {
        if (size == 0 || index >= size) throw new IndexOutOfBoundsException();
        CircleIterator iter = setIteratorToIndex(index);
        return iter.pull();
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

        private Node<E> current;
        private Node<E> prev;

        CircleIterator() {
            current = middleNode;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            prev = current;
            current = current.next;
            return prev.elem;
        }

        public void set(E elem) {
            if (prev == null) throw new IllegalStateException();
            prev.elem = elem;
        }

        @Override
        public void remove() {
            if (prev == null) throw new IllegalStateException();
            removeNode(prev);
        }

        public E pull() {
            if (prev == null) throw new IllegalStateException();
            return removeNode(prev);
        }

        private E removeNode(Node<E> node) {
            // MUST HANDLE 1-2 link removals
            if (node == middleNode) {
                middleNode = node.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size --;
            return node.elem;
        }
    }

    private class Node<T> {

        private T elem;
        private Node<T> next;
        private Node<T> prev;

        Node(T elem) {
            this.elem = elem;
        }
    }
}