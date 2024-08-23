package main.adapters.start;

/** Stores the state for the start screen. */
public class StartState {
    /** Stores an error message to display to the user. */
    private String error = null;

    /**
     * Retrieve the stored error message.
     *
     * @return the stored error message, or null if there is no message.
     */
    public Object getError() {
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
