package adapters.results;

import main.adapters.results.ResultsState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultsStateTest {
    private final String dummyResults = "some results here";
    private final String dummyError = "some error here";
    private ResultsState resultsState;

    @BeforeEach
    void setUp() {
        this.resultsState = new ResultsState();
    }

    @Test
    void getResults() {
        assertEquals("", resultsState.getResults());
    }

    @Test
    void getError() {
        assertNull(resultsState.getError());
    }

    @Test
    void setResults() {
        resultsState.setResults(dummyResults);
        assertEquals(dummyResults, resultsState.getResults());
        // assert that error is unchanged
        assertNull(resultsState.getError());
    }

    @Test
    void setError() {
        resultsState.setError(dummyError);
        assertEquals(dummyError, resultsState.getError());
        // assert that results unchanged
        assertEquals("", resultsState.getResults());
    }
}