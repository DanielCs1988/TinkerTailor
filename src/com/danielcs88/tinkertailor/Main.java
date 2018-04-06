package com.danielcs88.tinkertailor;

public class Main {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        CircleList<Integer> test = new CircleList<>();
        for (int i = 10; i > 0; i--) {
            test.add(i);
        }
        Integer result = game.playTinkerTailor(test, 7);
        System.out.println("\nAnd the winner is: " + result);
        test.clear();
        System.out.println("Content: " + test);
    }
}
