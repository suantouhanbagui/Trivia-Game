package main.adapters.two_player;

import main.adapters.play.PlayState;

public class TwoPlayerState extends PlayState {
    private String scores = "";
    private String turn = "";

    public String getScore() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }
}
