package main.use_case;

import main.data_access.ResultRecordingDataAccessObject;

import java.io.IOException;

/** Use case interactor for recording the results of a game. */
public class RecordResult {
    /** Data access object for recording results to a text file. */
    private ResultRecordingDataAccessObject resultRecorder;

    /** Instantiate a RecordResult new use case interactor by creating a ResultRecordingDataAccessObject. */
    public RecordResult() {
        try{
            this.resultRecorder = new ResultRecordingDataAccessObject();
        }
        catch (IOException ioException){
            System.out.println("Could not record result");
        }
    }

    /**
     * Records the results of a game into result.txt.
     *
     * @param game The game for which the results need to be recorded
     */
    public void record(GamePlayFunctionsInterface game) {
        try{
            resultRecorder.recordResult(game.getResults());
            resultRecorder.closeFile();
        }
        catch (IOException ioException) {
            System.out.println("Could not record result");
        }
    }
}
