package com.danielcs88.tinkertailor;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getInstance() {
        assertNotNull(Game.getInstance());
    }

    @Test
    void testTinkerTailorWithCircleList() {
        CircleList<Integer> players = new CircleList<>(Arrays.asList(5, 4, 3, 2, 1));
        int result = Game.getInstance().playTinkerTailor(players, 3);
        assertEquals(4, result);
    }

    @Test
    void testTinkerTailorWithArrayList() {
        List<Integer> players = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int result = Game.getInstance().playTinkerTailor(players, 3);
        assertEquals(4, result);
    }
}