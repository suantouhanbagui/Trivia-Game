package main.use_case.results;

/** Output data object for {@code ResultsOutputBoundary}. */
public class ResultsOutputData {
    /** Results to display to the user. */
    private final String results;

    /** Instantiate a new {@code ResultsOutputData} object. */
    public ResultsOutputData(String results) {
        this.results = results;
    }

    /**
     * Getter for the past results.
     *
     * @return past results.
     */
    public String getResults() {
        return results;
    }
}
