package use_case.play;

import main.data_access.ResultRetrievalDAO;
import main.use_case.results.ResultsInteractor;
import main.use_case.results.ResultsOutputBoundary;
import main.use_case.results.ResultsOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultsInteractorTest {

    private ResultsInteractor interactor;
    private TestResultsOutputBoundary outputBoundary;
    private TestResultRetrievalDAO resultRetrievalDAO;

    @BeforeEach
    void setUp() {
        outputBoundary = new TestResultsOutputBoundary();
        resultRetrievalDAO = new TestResultRetrievalDAO();
        interactor = new ResultsInteractor((ResultsOutputBoundary) outputBoundary, resultRetrievalDAO);
    }

    @Test
    void testPrepareViewSuccessful() throws IOException {
        // Set up mock data
        ArrayList<String> mockResults = new ArrayList<>();
        mockResults.add("Player1 scored 5/10");
        mockResults.add("Player2 scored 7/10");
        resultRetrievalDAO.setResults(mockResults);

        // Execute prepareView
        interactor.prepareView();

        // Verify output
        ResultsOutputData outputData = outputBoundary.getResultsOutputData();
        assertNotNull(outputData, "ResultsOutputData should not be null.");
        String expectedResult = "Player1 scored 5/10\nPlayer2 scored 7/10\n";
        assertEquals(expectedResult, outputData.getResults(), "The results should match the expected output.");
        assertFalse(outputBoundary.isFailViewPrepared(), "Fail view should not be prepared.");
        assertFalse(outputBoundary.isSuccessViewPrepared(), "Success view should not be prepared.");
    }

    @Test
    void testPrepareViewNoResults() throws IOException {
        // Set up mock data
        resultRetrievalDAO.setResults(new ArrayList<>());

        // Execute prepareView
        interactor.prepareView();

        // Verify output
        ResultsOutputData outputData = outputBoundary.getResultsOutputData();
        assertNotNull(outputData, "ResultsOutputData should not be null.");
        assertEquals("No results yet.", outputData.getResults(), "The results should indicate no results available.");
    }

    @Test
    void testPrepareViewIOException() throws IOException {
        // Set up to throw IOException
        resultRetrievalDAO.setThrowIOException(true);

        // Execute prepareView
        interactor.prepareView();

        // Verify fail view
        assertTrue(outputBoundary.isFailViewPrepared(), "Fail view should be prepared.");
        assertEquals("Could not retrieve results.", outputBoundary.getFailMessage(), "The fail message should match.");
        assertFalse(outputBoundary.isSuccessViewPrepared(), "Success view should not be prepared.");
    }

    @Test
    void testExecute() {
        // Execute the method
        interactor.execute();

        // Verify success view
        assertTrue(outputBoundary.isSuccessViewPrepared(), "Success view should be prepared.");
        assertFalse(outputBoundary.isFailViewPrepared(), "Fail view should not be prepared.");
    }

    private static class TestResultsOutputBoundary implements ResultsOutputBoundary {
        private ResultsOutputData resultsOutputData;
        private boolean successViewPrepared;
        private String failMessage;
        private boolean failViewPrepared;

        @Override
        public void prepareView(ResultsOutputData resultsOutputData) {
            this.resultsOutputData = resultsOutputData;
        }

        @Override
        public void prepareFailView(String error) {
            this.failViewPrepared = true;
            this.failMessage = error;
        }

        @Override
        public void prepareSuccessView() {
            this.successViewPrepared = true;
        }

        public ResultsOutputData getResultsOutputData() {
            return resultsOutputData;
        }

        public boolean isSuccessViewPrepared() {
            return successViewPrepared;
        }

        public boolean isFailViewPrepared() {
            return failViewPrepared;
        }

        public String getFailMessage() {
            return failMessage;
        }
    }

    private static class TestResultRetrievalDAO extends ResultRetrievalDAO {
        private ArrayList<String> results;
        private boolean throwIOException;

        @Override
        public ArrayList<String> retrieveResults() throws IOException {
            if (throwIOException) {
                throw new IOException("IOException occurred");
            }
            return results;
        }

        public void setResults(ArrayList<String> results) {
            this.results = results;
        }

        public void setThrowIOException(boolean throwIOException) {
            this.throwIOException = throwIOException;
        }
    }
}
