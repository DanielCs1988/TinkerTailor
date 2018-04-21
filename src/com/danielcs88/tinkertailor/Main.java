package com.danielcs88.tinkertailor;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        runGameWithCustomList(game);
        runGameWithArrayList(game);
        runTests();
    }

    private static void runGameWithCustomList(Game game) {
        CircleList<String> players = new CircleList<>(Arrays.asList(
                "Mythal (5)", "Andruil (4)", "Fen'Harel (3)", "Ghilan'nain (2)", "Dirthamen (1)"
        ));
        System.out.println("Players: " + players + "\n");
        System.out.println("Tinker, Tailor,\n" +
                           "Soldier, Sailor,\n" +
                           "Rich Man, Poor Man,\n" +
                           "Beggar Man, Thief!\n");
        System.out.println("Every third being falls!\n");

        String result = game.playTinkerTailor(players, 3);
        System.out.println("\nAnd the winner is: " + result + "\n");
    }

    private static void runGameWithArrayList(Game game) {
        List<String> players = new ArrayList<>(Arrays.asList(
                "Dirthamen (1)", "Ghilan'nain (2)", "Fen'Harel (3)", "Andruil (4)",  "Mythal (5)"
        ));
        System.out.println("Now the same game with a basic ArrayList collection:\n");
        String result = game.playTinkerTailor(players, 3);
        System.out.println("\nNo surprise here, the result is the same: " + result);
    }

    private static void runTests() {
        System.out.println("\n<<<<< And now some normals tests with a different data type! >>>>>\n");
        Test test = Test.getInstance();
        test.start();
    }
}
