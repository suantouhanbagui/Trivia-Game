package main.use_case.play;

/** Output boundary for the gameplay presenters. */
public interface PlayOutputBoundary {
    /**
     * Present user with feedback on the previous question if applicable, then
     * present the next question. Set the play view as the active view if not
     * already the case.
     *
     * @param playOutputData contains data to update the view with.
     */
    void prepareView(PlayOutputData playOutputData);

    /**
     * Return to the start view and present an error message indicating
     * something has gone wrong.
     *
     * @param error message that will be displayed to the user.
     */
    void prepareFailView(String error);

    /**
     * Remove feedback and error message from the playViewModel. Switch to the
     * start view and present the user with a message.
     *
     * @param message to present the user with.
     */
    void prepareSuccessView(String message);

    /**
     * Prompt the user to enter a nickname and return the name. Returns null if
     * no name was entered.
     *
     * @param message for the prompt.
     * @return name entered by the user, or null if nothing was entered.
     */
    String gatherName(String message);
}
