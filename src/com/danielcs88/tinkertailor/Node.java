package com.danielcs88.tinkertailor;

class Node<T> {

    private T elem;
    private Node<T> next;
    private Node<T> prev;

    Node(T elem) {
        this.elem = elem;
    }

    T getElem() {
        return elem;
    }

    void setElem(T elem) {
        this.elem = elem;
    }

    Node<T> getNext() {
        return next;
    }

    void setNext(Node<T> next) {
        this.next = next;
    }

    Node<T> getPrev() {
        return prev;
    }

    void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
