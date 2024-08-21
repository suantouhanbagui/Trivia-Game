package adapters.start;

import main.adapters.start.StartState;
import main.adapters.start.StartViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartViewModelTest {
    private StartViewModel startViewModel;
    private StartState testStartState;
    private String dummyError = "some error here";

    @BeforeEach
    void setUp() {
        this.startViewModel = new StartViewModel();
        this.testStartState = new StartState();
    }

    @Test
    void getState() {
        StartState actualState = startViewModel.getState();
        assertNull(actualState.getError());
    }

    @Test
    void setState() {
        testStartState.setError(dummyError);
        startViewModel.setState(testStartState);
        assertEquals(dummyError, startViewModel.getState().getError());
    }
}