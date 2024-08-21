package adapters.two_player;


import main.adapters.two_player.TwoPlayerState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoPlayerStateTest {
    // dummy data
    private String scores = "some scores";
    private String turn = "someone's turn";

    private TwoPlayerState twoPlayerState;


    @BeforeEach
    void setUp() {
        this.twoPlayerState = new TwoPlayerState();
    }

    @Test
    void getScore() {
        String actual = twoPlayerState.getScore();
        assertEquals("", actual);
    }

    @Test
    void setScores() {
        twoPlayerState.setScores(scores);
        String actual = twoPlayerState.getScore();
        assertEquals(scores, actual);
    }

    @Test
    void getTurn() {
        String actual = twoPlayerState.getTurn();
        assertEquals("", actual);
    }

    @Test
    void setTurn() {
        twoPlayerState.setTurn(turn);
        String actual = twoPlayerState.getTurn();
        assertEquals(turn, actual);
    }
}