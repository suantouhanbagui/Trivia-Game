package main.data_access;

import java.io.Closeable;
import java.io.IOException;

/**
 * Interface for data access objects that need to access the results file.
 */
public interface ResultFileAccessInterface {

    /**
     * Factory method for creating the object needed to access the file and
     * complete the task (for example, a writer to write onto the file).
     * @throws IOException when an I/O error occurs while creating the object.
     */
    Closeable createFileAccessor() throws IOException;

    /**
     * Closes the opened result records file.
     *
     * @throws IOException when an I/O error occurs while closing the file.
     */
    void closeFile() throws IOException;
}
