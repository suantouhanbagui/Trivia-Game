package use_case.results;

import main.use_case.results.ResultsOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultsOutputDataTest {
    private ResultsOutputData resultsOutputData;
    private final String results = "Some results here";

    @BeforeEach
    void setUp() {
        this.resultsOutputData = new ResultsOutputData(results);
    }

    @Test
    void getResults() {
        String actual = resultsOutputData.getResults();
        assertEquals(results, actual);
    }
}