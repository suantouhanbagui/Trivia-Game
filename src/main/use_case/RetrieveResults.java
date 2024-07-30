package main.use_case;

import main.data_access.ResultRetrievalDataAccessObject;

import java.io.IOException;
import java.util.ArrayList;

public class RetrieveResults {
    private final ResultRetrievalDataAccessObject resultsRetriever =
            new ResultRetrievalDataAccessObject();

    /** Instantiate a new use case interactor by creating a ResultRetrievalDataAccessObject.
     * @throws IOException when an I/O exception occurs
     */
    public RetrieveResults() throws IOException {
    }

    /**
     * Retrieves a list of the results of all the games played so far, from result.txt.
     *
     * @return a list of the results of all the previous games.
     * @throws IOException when an I/O exception occurs
     */
    public ArrayList<String> retrieve() throws IOException{
        ArrayList<String>

        resultList = resultsRetriever.retrieveResults();
        resultsRetriever.closeResultFile();
        return resultList;
    }
}
