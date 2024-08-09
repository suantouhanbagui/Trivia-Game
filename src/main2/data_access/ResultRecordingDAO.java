package main2.data_access;

import java.io.*;

/** A data access object for recording the results of a game.
 * TODO: Update javadoc.
 * */
public class ResultRecordingDAO {
    /**
     * Implementation of createFileAccessor from ResultFileAccessInterface.
     * Creates a BufferedWriter and assigns it to
     * @return a BufferedWriter that appends onto result record file.
     * @throws IOException when an I/O error occurs while creating the object.
     */
    public BufferedWriter createFileAccessor() throws IOException {
        File resultFile = new File("src\\main2\\data_access\\result.txt");
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
        BufferedWriter resultFileWriter = createFileAccessor();
        resultFileWriter.write(result + "\n");
        resultFileWriter.close();
    }
}
