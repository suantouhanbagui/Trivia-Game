package main.use_case;

import main.data_access.ResultRecordingDataAccessObject;

import java.io.IOException;

public class RecordResult {
    private ResultRecordingDataAccessObject resultRecorder;

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
