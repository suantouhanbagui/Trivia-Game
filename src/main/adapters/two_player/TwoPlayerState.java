package main.adapters.two_player;

import main.adapters.play.PlayState;

/** Stores the state for a two player gameplay view class. */
public class TwoPlayerState extends PlayState {
    /** String representation of the two players' scores. */
    private String scores = "";
    /** String to tell the players whose turn it is. */
    private String turn = "";

    /**
     * Get a String representation of the players' scores.
     *
     * @return String representation of players' scores.
     */
    public String getScore() {
        return scores;
    }

    /**
     * Set the String representation of the players' scores.
     *
     * @param scores new String representation of players' scores.
     */
    public void setScores(String scores) {
        this.scores = scores;
    }

    /**
     * Get a String to tell the players whose turn it is.
     *
     * @return String to tell the players whose turn it is.
     */
    public String getTurn() {
        return turn;
    }

    /**
     * Set the String that tells the players whose turn it is.
     *
     * @param turn new String to tell the players whose turn it is.
     */
    public void setTurn(String turn) {
        this.turn = turn;
    }
}
