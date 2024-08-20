package main.adapters.results;

import main.use_case.results.ResultsInputBoundary;
import main.use_case.results.ResultsInteractor;

/** Controller to pass data through the results input boundary. */
public class ResultsController {
    /** Use case interactor for results use case. */
    private final ResultsInputBoundary resultsInputBoundary;

    /**
     * Instantiate a new {@code ResultsController}.
     *
     * @param resultsInputBoundary Interactor to invoke when this controller
     *        is invoked.
     */
    public ResultsController(ResultsInteractor resultsInputBoundary) {
        this.resultsInputBoundary = resultsInputBoundary;
    }

    /**
     * Invoke {@code prepareView} on the input boundary, which sets the view
     * associated with this use case as the active view.
     */
    public void prepareView() {
        resultsInputBoundary.prepareView();
    }

    /**
     * Invoke the input boundary.
     */
    public void execute() {
        resultsInputBoundary.execute();
    }
}
