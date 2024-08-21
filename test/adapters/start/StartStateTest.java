package adapters.start;

import main.adapters.start.StartState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartStateTest {
    private StartState startState;
    private String dummyError = "some error here";

    @BeforeEach
    void setUp() {
        this.startState = new StartState();
    }

    @Test
    void getError() {
        assertNull(startState.getError());
    }

    @Test
    void setError() {
        startState.setError(dummyError);
        assertEquals(dummyError, startState.getError());
    }
}