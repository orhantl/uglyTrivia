package com.kata.playboard;

import com.google.common.collect.*;

import java.io.PrintStream;
import java.util.*;

import static com.kata.playboard.QuestionTopic.*;

public class Game {

    public static final int QUESTIONS_PER_TOPIC = 50;

    private final PrintStream out;
    List<Player> allPlayers = new ArrayList<>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    Map<QuestionTopic, LinkedList<String>> questions = new HashMap<>() {{
        put(POP, new LinkedList<>());
        put(SCIENCE, new LinkedList<>());
        put(SPORTS, new LinkedList<>());
        put(ROCK, new LinkedList<>());
    }};

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public  Game(PrintStream out){
        for(QuestionTopic topic : QuestionTopic.values()) {
            LinkedList<String> topicQuestions = questions.get(topic);
            for (int i = 0; i < QUESTIONS_PER_TOPIC; i++) {
                topicQuestions.addLast(String.format("%s Question %d", topic, i));
            }
        }
        this.out = out;
    }

    public boolean add(String playerName) {
        allPlayers.add(new Player(playerName));

//        players.add(playerName);
//        places[howManyPlayers()] = 0;
//        purses[howManyPlayers()] = 0;
//        inPenaltyBox[howManyPlayers()] = false;

        out.println(playerName + " was added");
        out.println("They are player number " + allPlayers.size());
        return true;
    }

    public void roll(int roll) {
        out.println(getCurrentPlayer() + " is the current player");
        out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                out.println(getCurrentPlayer() + " is getting out of the penalty box");
                moveCurrentPlayer(roll);
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

                out.println(getCurrentPlayer()
                        + "'s new location is "
                        + places[currentPlayer]);
                out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                out.println(getCurrentPlayer() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            moveCurrentPlayer(roll);
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            out.println(getCurrentPlayer()
                    + "'s new location is "
                    + places[currentPlayer]);
            out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void moveCurrentPlayer(int roll) {
        getCurrentPlayer().move(roll);
        // Parallel Run pattern
        places[currentPlayer] = places[currentPlayer] + roll;
    }

    private Player getCurrentPlayer() {
        return allPlayers.get(currentPlayer);
    }

    private void askQuestion() {
        out.println(questions.get(currentCategory()).removeFirst());
    }

    private QuestionTopic currentCategory() {
        return QuestionTopic.getTopicFromPosition(places[currentPlayer]);
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]){
            if (isGettingOutOfPenaltyBox) {
                out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                out.println(getCurrentPlayer()
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == allPlayers.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == allPlayers.size()) currentPlayer = 0;
                return true;
            }
        } else {

            out.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            out.println(getCurrentPlayer()
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == allPlayers.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer(){
        out.println("Question was incorrectly answered");
        out.println(getCurrentPlayer()+ " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == allPlayers.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
