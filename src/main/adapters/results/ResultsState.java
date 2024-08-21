package main.adapters.results;

public class ResultsState {
    /** Results that will be displayed to the user. */
    private String results = "";
    /** Stores an error message to display to the user. */
    private String error = null;

    /**
     * Getter for results.
     *
     * @return the results.
     */
    public String getResults() {
        return results;
    }

    /**
     * Setter for results.
     *
     * @param results new results.
     */
    public void setResults(String results) {
        this.results = results;
    }

    /**
     * Getter for the error message.
     *
     * @return the stored error message, or null if there is none.
     */
    public String getError() {
        return error;
    }

    /**
     * Set the error message.
     *
     * @param error new error message. Use null to indicate that there is no
     *        error message to display.
     */
    public void setError(String error) {
        this.error = error;
    }
}
