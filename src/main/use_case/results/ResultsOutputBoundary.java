package main.use_case.results;

public interface ResultsOutputBoundary {
    void prepareView(ResultsOutputData resultsOutputData);

    void prepareFailView(String error);

    void prepareSuccessView();
}
