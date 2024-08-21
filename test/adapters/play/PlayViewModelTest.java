package adapters.play;

import main.adapters.play.PlayState;
import main.adapters.play.PlayViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayViewModelTest {
    private PlayViewModel playViewModel;

    // dummy data
    private String progress = "Question 2 of 10";
    private String text = "some text";
    private String[] options = new String[4];
    private String feedback = "some feedback";
    private String error = "some error";


    @BeforeEach
    void setUp() {
        for (int i = 0; i < options.length; i++) {
            options[i] = "some option " + i;
        }
        this.playViewModel = new PlayViewModel();
    }

    @Test
    void getState() {
        PlayState expectedState = new PlayState();
        assertEquals(expectedState.getError(), playViewModel.getState().getError());
        assertEquals(expectedState.getFeedback(), playViewModel.getState().getFeedback());
        assertEquals(expectedState.getText(), playViewModel.getState().getText());
    }

    @Test
    void setState() {
        PlayState testState = new PlayState();
        testState.setError(error);
        testState.setFeedback(feedback);
        testState.setText(text);
        testState.setProgress(progress);
        testState.setOptions(options);
        playViewModel.setState(testState);
        assertEquals(error, playViewModel.getState().getError());
        assertEquals(feedback, playViewModel.getState().getFeedback());
        assertEquals(text, playViewModel.getState().getText());
        assertEquals(progress, playViewModel.getState().getProgress());
        assertEquals(options, playViewModel.getState().getOptions());
    }
}