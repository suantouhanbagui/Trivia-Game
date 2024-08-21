package main.use_case.results;

public class ResultsOutputData {
    /** Results to display to the user. */
    private final String results;

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
