package data_access;

import java.io.*;
import java.util.ArrayList;

public class ResultRetrievalDataAccessObject {
    private final BufferedReader resultsFileReader;

    public ResultRetrievalDataAccessObject() throws IOException {
        File resultFile = new File("src\\data_access\\result.txt");
        FileReader fileReader = new FileReader(resultFile);
        resultsFileReader = new BufferedReader(fileReader);
    }

    /**
     * Method to read the results file and return an ArrayList of all the results.
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
     * Method to close the opened results file.
     * @throws IOException if an I/O exception occurs.
     */
    public void closeResultFile() throws IOException{
        resultsFileReader.close();
    }
}
