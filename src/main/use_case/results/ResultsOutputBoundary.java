package main.use_case.results;

public interface ResultsOutputBoundary {
    /** Sets the results view as the active view. */
    void prepareView(ResultsOutputData resultsOutputData);

    /**
     * Present an error message to the user indicating the results could not be
     * retrieved.
     *
     * @param error message that will be displayed to the user.
     */
    void prepareFailView(String error);

    /**
     * Complete the interaction by returning to the start screen.
     */
    void prepareSuccessView();
}
