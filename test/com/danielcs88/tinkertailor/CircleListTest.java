package com.danielcs88.tinkertailor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CircleListTest {

    private CircleList<Integer> list;

    @BeforeEach
    void setupList() {
        list = new CircleList<>(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    void size() {
        assertEquals(5, list.size());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
    }

    @Test
    void iterator() {
        assertNotNull(list.iterator());
    }

    @Test
    void add() {
        list.add(6);
        list.add(7);
        String expected = "[7, 6, 5, 4, 3, 2, 1]";
        assertEquals(expected, list.toString());
    }

    @Test
    void insert() {
        list.insert(2, 69);
        String expected = "[5, 4, 69, 3, 2, 1]";
        assertEquals(expected, list.toString());
    }

    @Test
    void clear() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void forEach() {
        List<Integer> consumer = new ArrayList<>();
        list.forEach(consumer::add);
        String expected = "[5, 4, 3, 2, 1]";
        assertEquals(expected, consumer.toString());
    }

    @Test
    void get() {
        int value = list.get(2);
        assertEquals(3, value);
    }

    @Test
    void set() {
        list.set(1, 69);
        int value = list.get(1);
        assertEquals(69, value);
    }

    @Test
    void remove() {
        list.remove(3);
        String expected = "[5, 4, 3, 1]";
        assertEquals(expected, list.toString());
    }

    @Test
    void removeElement() {
        list.removeElement(2);
        String expected = "[5, 4, 3, 1]";
        assertEquals(expected, list.toString());
    }

    @Test
    void testToString() {
        String expected = "[5, 4, 3, 2, 1]";
        assertEquals(expected, list.toString());
    }
}