package main.use_case.settings;

public interface SettingsOutputBoundary {
    /** Sets the settings view as the active view. */
    void prepareView();

    /**
     * Present an error message to the user indicating the settings could not
     * be saved.
     *
     * @param error message that will be displayed to the user.
     */
    void prepareFailView(String error);

    /**
     * Complete the interaction by returning to the start screen.
     */
    void prepareSuccessView();
}
