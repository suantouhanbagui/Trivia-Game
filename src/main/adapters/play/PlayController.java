package main.adapters.play;

import main.use_case.play.PlayInputBoundary;
import main.use_case.play.PlayInputData;

/** Controller to pass data through the play output boundary. */
public class PlayController {
    /** Use case interactor for gameplay. */
    public PlayInputBoundary playInputBoundary;

    /**
     * Instantiate a new {@code PlayController}.
     *
     * @param playInputBoundary Interactor to invoke when this controller is
     *        invoked.
     */
    public PlayController(PlayInputBoundary playInputBoundary) {
        this.playInputBoundary = playInputBoundary;
    }

    /**
     * Invoke {@code prepareView} on the input boundary, which sets the view
     * associated with this use case as the active view.
     */
    public void prepareView() {
        playInputBoundary.prepareView();
    }

    /**
     * Pass input data containing the user's answer through the input boundary.
     *
     * @param answer user's answer.
     */
    public void execute(String answer) {
        PlayInputData inputData = new PlayInputData(answer);
        playInputBoundary.execute(inputData);
    }
}
