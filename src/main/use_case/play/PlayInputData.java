package main.use_case.play;

/** Input data object for {@code PlayInputBoundary}. */
public class PlayInputData {
    /** User's answer to the current question. */
    private final String answer;

    /**
     * Instantiate a new {@code PlayInputData}.
     *
     * @param answer user's answer to the current question.
     */
    public PlayInputData(String answer) {
        this.answer = answer;
    }

    /**
     * Retrieve the user's answer.
     *
     * @return The user's answer to the current question.
     */
    public String getAnswer() {
        return answer;
    }
}
