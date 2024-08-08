package main2.adapters.play;

import main2.use_case.play.PlayInputBoundary;
import main2.use_case.play.PlayInputData;

public class PlayController {
    public PlayInputBoundary playInputBoundary;

    public PlayController(PlayInputBoundary playInputBoundary) {
        this.playInputBoundary = playInputBoundary;
    }

    public void execute(String answer) {
        PlayInputData inputData = new PlayInputData(answer);
        playInputBoundary.execute(inputData);
    }

    public void prepareView() {
        playInputBoundary.prepareView();
    }
}
