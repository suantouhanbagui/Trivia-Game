package main2.adapters.results;

import main2.use_case.results.ResultsInputBoundary;
import main2.use_case.results.ResultsInteractor;

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
