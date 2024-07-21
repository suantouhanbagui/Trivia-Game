package main.data_access;

import java.io.*;
import java.util.ArrayList;

/** A data access object for retrieving the results of previous games. */
public class ResultRetrievalDataAccessObject {
    /** Reads from the result file. */
    private final BufferedReader resultsFileReader;

    /**
     * Instantiate a new ResultRetrievalDAO by opening the result file and
     * creating a reader for said file.
     *
     * @throws IOException when opening of the text file or creation of the
     *         reader fails.
     */
    public ResultRetrievalDataAccessObject() throws IOException {
        File resultFile = new File("src\\main.data_access\\result.txt");
        FileReader fileReader = new FileReader(resultFile);
        resultsFileReader = new BufferedReader(fileReader);
    }

    /**
     * Read the results file and return an ArrayList of all the results.
     *
     * @return An ArrayList of Strings representing all the quiz results that
     *         have been written into the result record file.
     * @throws IOException if an I/O exception occurs.
     */
    public ArrayList<String> retrieveResults() throws IOException{
        ArrayList<String> results = new ArrayList<>();
        String line = resultsFileReader.readLine();
        do {
            results.add(line);
            line = resultsFileReader.readLine();
        } while (line != null);
        return results;
    }

    /**
     * Close the opened results file.
     *
     * @throws IOException if an I/O exception occurs.
     */
    public void closeResultFile() throws IOException{
        resultsFileReader.close();
    }
}
