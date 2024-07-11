package use_case;

import data_access.ResultRetrievalDataAccessObject;

import java.io.IOException;
import java.util.ArrayList;

public class RetrieveResults {
    private ResultRetrievalDataAccessObject resultsRetriever;

    public RetrieveResults() {
        try{
            resultsRetriever = new ResultRetrievalDataAccessObject();
        }
        catch (IOException ioException){
            System.out.println("Could not retrieve results");
        }
    }

    /**
     * Retrieves a list of the results of all the games played so far, from result.txt.
     * @return a list of the results of all the previous games.
     */
    public ArrayList<String> retrieve(){
        ArrayList<String> resultList = new ArrayList<>();
        try{
            resultList = resultsRetriever.retrieveResults();
            resultsRetriever.closeResultFile();
        }
        catch(IOException ioException){
            System.out.println("Could not retrieve results");
        }
       return resultList;
    }
}