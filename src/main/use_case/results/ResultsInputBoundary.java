package main.use_case.results;

/** Input boundary for results use case. */
public interface ResultsInputBoundary {
    /**
     * Retrieve past results using {@code resultRetrievalDAO} and pass them
     * through the output boundary. Also set the view associated with the
     * results use case to the active view.
     */
    void prepareView();

    /** Returns to the start screen. */
    void execute();
}
