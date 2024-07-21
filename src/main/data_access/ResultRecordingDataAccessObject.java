package main.data_access;

import java.io.*;

public class ResultRecordingDataAccessObject {
    private final BufferedWriter resultFileWriter;

    public ResultRecordingDataAccessObject() throws IOException {
        File resultFile = new File("src\\main.data_access\\result.txt");
        FileWriter fileWriter = new FileWriter(resultFile,true);
        resultFileWriter = new BufferedWriter(fileWriter);
    }

    /**
     * Write the quiz result into the result records file.
     * @param result The quiz result to be written into the result records file.
     * @throws IOException when an I/O exception occurs.
     */
    public void recordResult(String result) throws IOException{
        resultFileWriter.write(result + "\n");
    }

    /**
     * Closes the opened result records file.
     * @throws IOException when an I/O exception occurs.
     */
    public void closeFile() throws IOException{
        resultFileWriter.close();
    }
}
