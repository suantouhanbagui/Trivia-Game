package data_access;

import main.data_access.ResultRetrievalDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultRetrievalDAOTest {

    private File resultFile;

    @BeforeEach
    public void setUp() throws IOException {
        resultFile = new File("src/main/data_access/result.txt");
        // Ensure the file is empty before each test
        if (resultFile.exists()) {
            resultFile.delete();
        }
        resultFile.createNewFile();

        // Write some sample data to the file for testing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile, true))) {
            writer.write("Result 1");
            writer.newLine();
            writer.write("Result 2");
            writer.newLine();
            writer.write("Result 3");
            writer.newLine();
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (resultFile.exists()) {
            resultFile.delete();
        }
    }

    @Test
    public void testRetrieveResults() throws IOException {
        ResultRetrievalDAO dao = new ResultRetrievalDAO();
        ArrayList<String> results = dao.retrieveResults();

        // Expected results
        ArrayList<String> expectedResults = new ArrayList<>();
        expectedResults.add("Result 1");
        expectedResults.add("Result 2");
        expectedResults.add("Result 3");

        // Verify the results are as expected
        assertEquals(expectedResults, results, "The retrieved results do not match the expected results.");
    }
}
