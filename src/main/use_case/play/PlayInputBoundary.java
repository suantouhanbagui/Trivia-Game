package main.use_case.play;

/** Input boundary for gameplay use case interactors */
public interface PlayInputBoundary {
    /**
     * Start a new game, then set the view associated with the gameplay use
     * case as the active view.
     */
    void prepareView();

    /**
     * Present a feedback message that tells the user if their answer is
     * correct, then load the next question. If there are no questions left,
     * go back to the starting screen and display the results of the game
     * instead of loading a new question.
     *
     * @param inputData contains the user's answer to the question.
     */
    void execute(PlayInputData inputData);
}
