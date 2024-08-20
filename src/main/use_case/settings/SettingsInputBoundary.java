package main.use_case.settings;

public interface SettingsInputBoundary {
    /** Set the view associated with the settings use case as the active view. */
    void prepareView();

    /**
     * Changes theme and alerts the {@code PlayInteractor}s of the setting
     * changes.
     *
     * @param inputData contains all settings selected by the user.
     */
    void execute(SettingsInputData inputData);
}
