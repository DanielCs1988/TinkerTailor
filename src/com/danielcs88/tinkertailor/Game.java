package com.danielcs88.tinkertailor;

import java.util.Iterator;

public final class Game {

    private static Game instance = new Game();

    private Game() {}

    public static Game getInstance() {
        return instance;
    }

    public <T> T playTinkerTailor(CircleList<T> players, int steps) {
        Iterator<T> playerIterator = players.iterator();
        int rounds = players.size();

        System.out.println(players);

        for (int i = 0; i < rounds - 1; i++) {
            for (int j = 0; j < steps; j++) {
                if (i == 0 && j == 0) continue;
                playerIterator.next();
            }
            playerIterator.remove();
            System.out.println(players);
        }
        return players.get(0);
    }
}
