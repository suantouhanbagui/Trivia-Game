package main.use_case.results;

import main.data_access.ResultRetrievalDAO;

import java.io.IOException;
import java.util.ArrayList;

public class ResultsInteractor implements ResultsInputBoundary {
    private final ResultsOutputBoundary resultsOutputBoundary;
    private final ResultRetrievalDAO resultRetrievalDAO;

    public ResultsInteractor(ResultsOutputBoundary resultsOutputBoundary,
                             ResultRetrievalDAO resultRetrievalDAO) {
        this.resultsOutputBoundary = resultsOutputBoundary;
        this.resultRetrievalDAO = resultRetrievalDAO;
    }

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

    @Override
    public void execute() {
        resultsOutputBoundary.prepareSuccessView();
    }
}
