package main.adapters.play;

/** Stores the state for a gameplay view class. */
public class PlayState {
    /** String representation of progress through the quiz. */
    private String progress = "Question 1 of 10";
    /** Text for the question current. */
    private String text = "";
    /** Answers for the current question. */
    private String[] options = new String[4];

    /**
     * Stores a feedback message to tell the user if they answered the previous
     * question correctly. If they were incorrect, include the correct answer
     * in the message. If this is the first question of the quiz, this should
     * be set to null to indicate that there is no feedback.
     */
    private String feedback = null;
    /** Stores an error message to display to the user. */
    private String error = null;

    /**
     * Return a String representation of progress through the current quiz.
     *
     * @return String representation of progress through the current quiz.
     */
    public String getProgress() {
        return progress;
    }

    /**
     * Changes the String that represents current progress through the quiz.
     *
     * @param progress through the quiz represented as a string.
     */
    public void setProgress(String progress) {
        this.progress = progress;
    }

    /**
     * Get the text for the current question.
     *
     * @return text for the current question.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text for the current question.
     *
     * @param text for the current question.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the possible answers to the current question for the user to
     * choose from.
     *
     * @return array of String containing the answer options for the current
     *         question.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Set the possible answers.
     *
     * @param options contains the options for answering the question.
     */
    public void setOptions(String[] options) {
        this.options = options;
    }

    /**
     * Retrieve the stored feedback message from the previous question.
     *
     * @return a feedback message to tell the user if they answered the
     *         previous question correctly. Returns null if there is no
     *         message.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Set the feedback message.
     *
     * @param feedback message for the previous question. Use null to indicate
     *        that there is no feedback message to display.
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Retrieve the stored error message.
     *
     * @return the stored error message, or null if there is no message.
     */
    public String getError() {
        return error;
    }

    /**
     * Set the error message.
     *
     * @param error new error message. Use null to indicate that there is no
     *        error message to display.
     */
    public void setError(String error) {
        this.error = error;
    }
}
