package com.kata.uglytrivia;

import com.kata.playboard.*;

import java.io.PrintStream;
import java.util.*;

public class GameRunner {

    private static boolean isAWinner;

    public static void main(String[] args) {
        run(new Random(), System.out);

    }

    protected static void run(Random rand, PrintStream out) {
        Game aGame = new Game(out);

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                isAWinner = aGame.wrongAnswer();
            } else {
                isAWinner = aGame.wasCorrectlyAnswered();
            }

        } while (!isAWinner);
    }
}
