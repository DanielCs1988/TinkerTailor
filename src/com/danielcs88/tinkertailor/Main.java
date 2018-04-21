package com.danielcs88.tinkertailor;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Game game = Game.getInstance();
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
        System.out.println("\nAnd the winner is: " + result);

        System.out.println("\n<<<<< And now some normals tests with a different data type! >>>>>\n");
        Test test = Test.getInstance();
        test.start();
    }
}
