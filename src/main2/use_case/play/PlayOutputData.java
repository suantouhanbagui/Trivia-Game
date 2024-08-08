package main2.use_case.play;

import main2.entities.Player;
import main2.entities.Question;

public class PlayOutputData {
    private final int amount;
    private final int index;
    private final Question nextQuestion;
    private final Player[] players;
    private final Boolean previousCorrect;
    private final String previousAnswer;

    public PlayOutputData(int amount, int index, Question nextQuestion,
                          Player[] players, Boolean previousCorrect,
                          String previousAnswer) {
        this.amount = amount;
        this.index = index;
        this.nextQuestion = nextQuestion;
        this.players = players;
        this.previousCorrect = previousCorrect;
        this.previousAnswer = previousAnswer;
    }

    public int getAmount() {
        return amount;
    }

    public int getIndex() {
        return index;
    }

    public Question getNextQuestion() {
        return nextQuestion;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Boolean getPreviousCorrect() {
        return previousCorrect;
    }

    public String getPreviousAnswer() {
        return previousAnswer;
    }
}
