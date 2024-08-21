package adapters.play;

import main.adapters.play.PlayState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayStateTest {
    // dummy data
    private String progress = "Question 2 of 10";
    private String text = "some text";
    private String[] options = new String[4];
    private String feedback = "some feedback";
    private String error = "some error";

    private PlayState playState;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < options.length; i++) {
            options[i] = "some option " + i;
        }
        this.playState = new PlayState();
    }

    @Test
    void getProgress() {
        assertEquals("Question 1 of 10", playState.getProgress());
    }

    @Test
    void setProgress() {
        playState.setProgress(progress);
        assertEquals(progress, playState.getProgress());
    }

    @Test
    void getText() {
        assertEquals("", playState.getText());
    }

    @Test
    void setText() {
        playState.setText(text);
        assertEquals(text, playState.getText());
    }

    @Test
    void getOptions() {
        assertArrayEquals(new String[4], playState.getOptions());
    }

    @Test
    void setOptions() {
        playState.setOptions(options);
        assertArrayEquals(options, playState.getOptions());
    }

    @Test
    void getError() {
        assertNull(playState.getError());
    }

    @Test
    void setError() {
        playState.setError(error);
        assertEquals(error, playState.getError());
    }

    @Test
    void getFeedback() {
        assertNull(playState.getFeedback());
    }

    @Test
    void setFeedback() {
        playState.setFeedback(feedback);
        assertEquals(feedback, playState.getFeedback());
    }
}