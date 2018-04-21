package com.danielcs88.tinkertailor;

import java.util.*;
import java.util.function.Consumer;

public class CircleList<E> {

    private int size;
    private Node<E> middleNode;

    public CircleList() {}

    public CircleList(Iterable<E> iterable) {
        iterable.forEach(this::add);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<E> iterator() {
        return new CircleIterator();
    }

    public void add(E elem) {
        Node<E> newNode = new Node<>(elem);
        if (middleNode != null) {
            newNode.next = middleNode;
            if (middleNode.next != null) {
                newNode.prev = middleNode.prev;
                newNode.prev.next = newNode;
            } else {
                newNode.prev = middleNode;
                middleNode.next = newNode;
            }
            middleNode.prev = newNode;
        }
        middleNode = newNode;
        size++;
    }

    public void insert(int index, E elem) {
        checkIndexValidity(index);
        if (index == 0) add(elem);

        Node<E> newNode = new Node<>(elem);
        CircleIterator iter = setIteratorToIndex(index - 1);
        newNode.prev = iter.current.prev;
        newNode.next = iter.current;
        newNode.prev.next = newNode;
        iter.current.prev = newNode;
        size++;
    }

    public void clear() {
        for (Node<E> node = middleNode; node != null; ) {
            Node<E> next = node.next;
            unlink(node);
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

    public E get(int index) {
        checkIndexValidity(index);
        Iterator<E> iter = setIteratorToIndex(index - 1);
        return iter.next();
    }

    public void set(int index, E element) {
        checkIndexValidity(index);
        CircleIterator iter = setIteratorToIndex(index);
        iter.set(element);
    }

    public E remove(int index) {
        checkIndexValidity(index);
        CircleIterator iter = setIteratorToIndex(index);
        return iter.pop();
    }

    public E removeElement(E elem) {
        CircleIterator iter = new CircleIterator();
        for (int i = 0; i < size; i++) {
            if (iter.next().equals(elem)) {
                return iter.pop();
            }
        }
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

    private E unlink(Node<E> node) {
        node.prev = null;
        node.next = null;
        E retVal = node.elem;
        node.elem = null;
        size--;
        return retVal;
    }

    private CircleIterator setIteratorToIndex(int index) {
        CircleIterator iter = new CircleIterator();
        for (int i = 0; i <= index; i++) {
            iter.next();
        }
        return iter;
    }

    private void checkIndexValidity(int index) {
        if (size == 0 || index >= size || index < 0) throw new IndexOutOfBoundsException();
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
            Node<E> temp = prev.prev;
            removeNode(prev);
            prev = temp;
        }

        public E pop() {
            if (prev == null) throw new IllegalStateException();
            Node<E> temp = prev.prev;
            E retVal = removeNode(prev);
            prev = temp;
            return retVal;
        }

        private E removeNode(Node<E> node) {
            if (node.next == null) {
                // When the last node is being removed, we just extract it's value and null out everything.
                E retVal = node.elem;
                node.elem = null;
                middleNode = null;
                current = null;
                size = 0;
                return retVal;
            }
            if (node == middleNode) {
                middleNode = node.next;
            }
            if (node.next == node.prev) {
                // When only 2 links remain, the links are nullified instead of redirecting them inwards.
                node.next.next = null;
                node.next.prev = null;
            } else {
                // Redirecting the surrounding links to skip the link being removed.
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            return unlink(node);
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