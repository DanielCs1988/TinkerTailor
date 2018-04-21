package com.danielcs88.tinkertailor;

import java.util.*;

public final class Game {

    private static Game instance = new Game();

    private Game() {}

    public static Game getInstance() {
        return instance;
    }

    public <T> T playTinkerTailor(CircleList<T> players, int steps) {
        Iterator<T> playerIterator = players.iterator();
        int rounds = players.size();

        for (int i = 0; i < rounds - 1; i++) {
            System.out.println(players);
            for (int j = 0; j < steps; j++) {
                playerIterator.next();
            }
            playerIterator.remove();
        }
        return players.get(0);
    }

    public <T> T playTinkerTailor(List<T> players, int steps) {
        int rounds = players.size();
        Iterator<T> iter = players.iterator();

        for (int i = 0; i < rounds - 1; i++) {
            System.out.println(players);
            for (int j = 0; j < steps; j++) {
                if (!iter.hasNext()) {
                    iter = players.iterator();
                }
                iter.next();
            }
            iter.remove();
        }
        return players.get(0);
    }
}
