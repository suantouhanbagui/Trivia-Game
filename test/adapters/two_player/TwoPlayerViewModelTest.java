package adapters.two_player;


import main.adapters.two_player.TwoPlayerState;
import main.adapters.two_player.TwoPlayerViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoPlayerViewModelTest {
    private TwoPlayerViewModel twoPlayerViewModel;

    // dummy data
    private String progress = "Question 2 of 10";
    private String text = "some text";
    private String[] options = new String[4];
    private String feedback = "some feedback";
    private String error = "some error";
    private String scores = "some scores";
    private String turn = "someone's turn";

    private TwoPlayerState twoPlayerState;


    @BeforeEach
    void setUp() {
        for (int i = 0; i < options.length; i++) {
            options[i] = "some option " + i;
        }
        this.twoPlayerViewModel = new TwoPlayerViewModel();
    }
    @Test
    void getState() {
        TwoPlayerState expectedState = new TwoPlayerState();
        assertEquals(expectedState.getError(), twoPlayerViewModel.getState().getError());
        assertEquals(expectedState.getFeedback(), twoPlayerViewModel.getState().getFeedback());
        assertEquals(expectedState.getText(), twoPlayerViewModel.getState().getText());
        assertEquals(expectedState.getProgress(), twoPlayerViewModel.getState().getProgress());
        assertEquals(expectedState.getScore(), twoPlayerViewModel.getState().getScore());
        assertEquals(expectedState.getTurn(), twoPlayerViewModel.getState().getTurn());
    }

    @Test
    void setState() {
        TwoPlayerState testState = new TwoPlayerState();
        testState.setError(error);
        testState.setFeedback(feedback);
        testState.setText(text);
        testState.setProgress(progress);
        testState.setOptions(options);
        testState.setScores(scores);
        testState.setTurn(turn);

        twoPlayerViewModel.setState(testState);
        assertEquals(error, twoPlayerViewModel.getState().getError());
        assertEquals(feedback, twoPlayerViewModel.getState().getFeedback());
        assertEquals(text, twoPlayerViewModel.getState().getText());
        assertEquals(progress, twoPlayerViewModel.getState().getProgress());
        assertEquals(options, twoPlayerViewModel.getState().getOptions());
        assertEquals(scores, twoPlayerViewModel.getState().getScore());
        assertEquals(turn, twoPlayerViewModel.getState().getTurn());
    }
}