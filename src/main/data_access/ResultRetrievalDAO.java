package main.data_access;

import java.io.*;
import java.util.ArrayList;

/** A data access object for retrieving the results of previous games. */
public class ResultRetrievalDAO {
    /**
     * Creates a BufferedReader using the results file.
     *
     * @return a BufferedReader that reads from the result record file.
     * @throws IOException when an I/O error occurs while creating the object.
     */
    public BufferedReader createFileAccessor() throws IOException {
        File resultFile = new File("src\\main\\data_access\\result.txt");
        FileReader fileReader = new FileReader(resultFile);
        return new BufferedReader(fileReader);
    }

    /**
     * Read the results file and return an ArrayList of all the results.
     *
     * @return An ArrayList of Strings representing all the quiz results that
     *         have been written into the result record file.
     * @throws IOException if an I/O exception occurs.
     */
    public ArrayList<String> retrieveResults() throws IOException{
        BufferedReader resultsFileReader = createFileAccessor();
        ArrayList<String> results = new ArrayList<>();
        String line = resultsFileReader.readLine();
        while (line != null) {
            results.add(line);
            line = resultsFileReader.readLine();
        }
        resultsFileReader.close();
        return results;
    }
}