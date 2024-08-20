package main.data_access;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultRecordingDAOTest {

    private File resultFile;

    @BeforeEach
    public void setUp() throws IOException {
        resultFile = new File("src/main/data_access/result.txt");
        // Ensure the file is empty before each test
        if (resultFile.exists()) {
            Files.delete(Paths.get(resultFile.getPath()));
        }
        resultFile.createNewFile();
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (resultFile.exists()) {
            Files.delete(Paths.get(resultFile.getPath()));
        }
    }

    @Test
    public void testRecordResult() throws IOException {
        ResultRecordingDAO dao = new ResultRecordingDAO();
        dao.recordResult("Test Result");

        // Verify the file contains the expected content
        try (BufferedReader reader = new BufferedReader(new FileReader(resultFile))) {
            String line = reader.readLine();
            assertEquals("Test Result", line);
        }
    }
}
