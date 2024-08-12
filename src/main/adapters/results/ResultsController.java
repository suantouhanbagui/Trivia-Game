package main.adapters.results;

import main.use_case.results.ResultsInputBoundary;
import main.use_case.results.ResultsInteractor;

public class ResultsController {
    private final ResultsInputBoundary resultsInputBoundary;

    public ResultsController(ResultsInteractor resultsInputBoundary) {
        this.resultsInputBoundary = resultsInputBoundary;
    }

    public void prepareView() {
        resultsInputBoundary.prepareView();
    }

    public void execute() {
        resultsInputBoundary.execute();
    }
}
