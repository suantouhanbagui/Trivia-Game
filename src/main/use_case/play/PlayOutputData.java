package main.use_case.play;

import main.entities.Player;
import main.entities.Question;

/** Output data object for {@code PlayOutputBoundary}. */
public class PlayOutputData {
    /** Total number of questions for this quiz. */
    private final int amount;
    /** Number of questions that have already been answered. */
    private final int index;
    /** Next question to present to the user. */
    private final Question nextQuestion;
    /** Players of the current game. */
    private final Player[] players;
    /**
     * True if the answer to the previous question was correct, or null if
     * this is the first question.
     */
    private final Boolean previousCorrect;
    /** Answer to the previous question. */
    private final String previousAnswer;

    /** Instantiate a new {@code PlayOutputData} object.
     *
     * @param amount total number of questions for this quiz.
     * @param index number of questions that have already been answered.
     * @param nextQuestion to present to the user.
     * @param players of the current game.
     * @param previousCorrect true if the answer to the previous question was
     *        correct, or null if this is the first question.
     * @param previousAnswer the answer to the previous question.
     */
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

    /**
     * Getter for amount.
     *
     * @return the total number of question in this quiz.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter for index.
     *
     * @return the number of questions that have already been answered.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Get the next question.
     *
     * @return the next question for the user to answer.
     */
    public Question getNextQuestion() {
        return nextQuestion;
    }

    /**
     * Get the players of the current game.
     *
     * @return the players of the current game in an array.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Get the result for the previous question.
     *
     * @return true if answer to the previous question was correct, null if
     *         this is the first question, or false otherwise.
     */
    public Boolean getPreviousCorrect() {
        return previousCorrect;
    }

    /**
     * Get the answer to the previous question.
     *
     * @return the answer to the previous question, or null if this is the
     * first question.
     */
    public String getPreviousAnswer() {
        return previousAnswer;
    }
}
