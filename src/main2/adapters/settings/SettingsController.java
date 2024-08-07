package main2.adapters.settings;

import main2.use_case.settings.SettingsInputBoundary;
import main2.use_case.settings.SettingsInputData;

public class SettingsController {
    private final SettingsInputBoundary settingsInputBoundary;

    public SettingsController(SettingsInputBoundary settingsInputBoundary) {
        this.settingsInputBoundary = settingsInputBoundary;
    }

    public void prepareView() {
        settingsInputBoundary.prepareView();
    }

    public void execute(boolean darkModeEnabled, String gamemode, String amount,
                        String category, String difficulty, String type) {
        SettingsInputData settingsInputData = new SettingsInputData(darkModeEnabled, gamemode,
                amount, category, difficulty, type);
        settingsInputBoundary.execute(settingsInputData);
    }
}
