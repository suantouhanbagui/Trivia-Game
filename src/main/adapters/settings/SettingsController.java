package main.adapters.settings;

import main.use_case.settings.SettingsInputBoundary;
import main.use_case.settings.SettingsInputData;

/** Controller to pass data through the settings input boundary. */
public class SettingsController {
    /** Use case interactor for changing settings. */
    private final SettingsInputBoundary settingsInputBoundary;

    /**
     * Instantiate a new {@code SettingsController}.
     *
     * @param settingsInputBoundary Interactor to invoke when this controller
     *        is invoked.
     */
    public SettingsController(SettingsInputBoundary settingsInputBoundary) {
        this.settingsInputBoundary = settingsInputBoundary;
    }

    /**
     * Invoke {@code prepareView} on the input boundary, which sets the view
     * associated with this use case as the active view.
     */
    public void prepareView() {
        settingsInputBoundary.prepareView();
    }

    /**
     * Pass data through the input boundary.
     *
     * @param amount of questions selected by the user.
     * @param category selected by the user.
     * @param difficulty selected by the user.
     * @param type selected by the user.
     * @param gamemode selected by the user.
     * @param darkMode selected by the user.
     */
    public void execute(String amount, String category, String difficulty,
                        String type, String gamemode, String darkMode) {
        SettingsInputData settingsInputData = new SettingsInputData(amount, category,
                difficulty, type, gamemode, darkMode);
        settingsInputBoundary.execute(settingsInputData);
    }
}
