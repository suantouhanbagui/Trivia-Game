package main.data_access;

import java.io.*;

/** A data access object for recording the results of a game */
public class ResultRecordingDataAccessObject implements  ResultFileAccessInterface{
    /** Writes to the result file. */
    private final BufferedWriter resultFileWriter;

    /**
     * Instantiate a new ResultRecordingDAO by opening/creating the results
     * file and creating a writer for said file.
     *
     * @throws IOException when creation of the text file or writer fails.
     */
    public ResultRecordingDataAccessObject() throws IOException {
        resultFileWriter = createFileAccessor();
    }

    /**
     * Implementation of createFileAccessor from ResultFileAccessInterface.
     * Creates a BufferedWriter and assigns it to
     * @return a BufferedWriter that appends onto result record file.
     * @throws IOException when an I/O error occurs while creating the object.
     */
    @Override
    public BufferedWriter createFileAccessor() throws IOException {
        File resultFile = new File("src\\main\\main2.data_access\\result.txt");
        FileWriter fileWriter = new FileWriter(resultFile,true);
        return new BufferedWriter(fileWriter);
    }

    /**
     * Write the quiz result into the result records file.
     *
     * @param result The quiz result to be written into the result records file.
     * @throws IOException when an I/O exception occurs.
     */
    public void recordResult(String result) throws IOException{
        resultFileWriter.write(result + "\n");
    }


    /**
     * Closes the opened result records file.
     *
     * @throws IOException when an I/O exception occurs.
     */
    public void closeFile() throws IOException{
        resultFileWriter.close();
    }
}
