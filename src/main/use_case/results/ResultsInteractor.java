package main.use_case.results;

import main.data_access.ResultRetrievalDAO;

import java.io.IOException;
import java.util.ArrayList;

/** Interactor for the results use case. */
public class ResultsInteractor implements ResultsInputBoundary {
    /** Presenter for interacting with the view model. */
    private final ResultsOutputBoundary resultsOutputBoundary;
    /** DAO for retrieving past results */
    private final ResultRetrievalDAO resultRetrievalDAO;

    /**
     * Instantiate a new {@code SettingsInteractor}.
     *
     * @param resultsOutputBoundary the presenter that interacts with the view
     *        model to display results to the user.
     * @param resultRetrievalDAO for retrieving past results.
     */
    public ResultsInteractor(ResultsOutputBoundary resultsOutputBoundary,
                             ResultRetrievalDAO resultRetrievalDAO) {
        this.resultsOutputBoundary = resultsOutputBoundary;
        this.resultRetrievalDAO = resultRetrievalDAO;
    }

    /**
     * Retrieve past results using {@code resultRetrievalDAO} and pass them
     * through the output boundary. Also set the view associated with the
     * results use case to the active view.
     */
    @Override
    public void prepareView() {
        try {
            StringBuilder results = new StringBuilder();
            ArrayList<String> resultsList = resultRetrievalDAO.retrieveResults();
            for (String s : resultsList) {
                results.append(s).append("\n");
            }
            String result;
            if (results.length() == 0) {
                result = "No results yet.";
            } else {
                result = results.toString();
            }
            ResultsOutputData outputData = new ResultsOutputData(result);
            resultsOutputBoundary.prepareView(outputData);
        } catch (IOException e) {
            resultsOutputBoundary.prepareView(new ResultsOutputData(""));
            resultsOutputBoundary.prepareFailView("Could not retrieve results.");
        }
    }

    /** Returns to the start screen. */
    @Override
    public void execute() {
        resultsOutputBoundary.prepareSuccessView();
    }
}
