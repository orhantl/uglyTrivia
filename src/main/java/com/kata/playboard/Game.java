package com.kata.playboard;


import java.io.PrintStream;
import java.util.*;

import static com.kata.playboard.QuestionTopic.*;

public class Game {
    // extraire le comportement de player
    // notion de Game à faire ressortir de facon plus explicite dans le code
    // creer une classe pour l'ensemble des players ?
    // améliorer la couverture de test - use case particulier

    public static final int QUESTIONS_PER_TOPIC = 50;

    private final PrintStream out;
    List<Player> allPlayers = new ArrayList<>();
    int currentPlayerIndex = 0;

    Map<QuestionTopic, LinkedList<String>> questions = new HashMap<>() {{
        put(POP, new LinkedList<>());
        put(SCIENCE, new LinkedList<>());
        put(SPORTS, new LinkedList<>());
        put(ROCK, new LinkedList<>());
    }};

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
        Player currentPlayer = getCurrentPlayer();
        out.println(currentPlayer + " is the current player");
        out.println("They have rolled a " + roll);

        if (getCurrentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                getCurrentPlayer().setGettingOutOfPenaltyBox(true);

                out.println(currentPlayer + " is getting out of the penalty box");
                currentPlayer.move(roll);

                out.println(currentPlayer
                        + "'s new location is "
                        + currentPlayer.place());
                out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                out.println(currentPlayer + " is not getting out of the penalty box");
                getCurrentPlayer().setGettingOutOfPenaltyBox(false);
            }

        } else {
            currentPlayer.move(roll);
            out.println(currentPlayer
                    + "'s new location is "
                    + currentPlayer.place());
            out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private Player getCurrentPlayer() {
        return allPlayers.get(currentPlayerIndex);
    }

    private void askQuestion() {
        out.println(questions.get(currentCategory()).removeFirst());
    }

    private QuestionTopic currentCategory() {
        return QuestionTopic.getTopicFromPosition(getCurrentPlayer().place());
    }

    public boolean wasCorrectlyAnswered() {
        boolean winner;
        if (getCurrentPlayer().isInPenaltyBox()){
            if (getCurrentPlayer().isGettingOutOfPenaltyBox()) {
                out.println("Answer was correct!!!!");
                getCurrentPlayer().addPoint();

                out.println(getCurrentPlayer()
                        + " now has "
                        + getCurrentPlayer().purse()
                        + " Gold Coins.");

                winner = didPlayerWin();
            } else {
                winner = false;
            }
        } else {

            out.println("Answer was correct!!!!");
            getCurrentPlayer().addPoint();
            out.println(getCurrentPlayer()
                    + " now has "
                    + getCurrentPlayer().purse()
                    + " Gold Coins.");

            winner = didPlayerWin();
        }
        nextPlayer();
        return winner;
    }

    private void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == allPlayers.size()) currentPlayerIndex = 0;
    }

    public boolean wrongAnswer(){
        out.println("Question was incorrectly answered");
        out.println(getCurrentPlayer()+ " was sent to the penalty box");

        getCurrentPlayer().goToPenaltyBox();

        nextPlayer();
        return false;
    }


    private boolean didPlayerWin() {
        return getCurrentPlayer().hasWon();
    }
}
