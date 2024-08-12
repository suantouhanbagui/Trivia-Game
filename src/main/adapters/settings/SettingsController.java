package main.adapters.settings;

import main.use_case.settings.SettingsInputBoundary;
import main.use_case.settings.SettingsInputData;

public class SettingsController {
    private final SettingsInputBoundary settingsInputBoundary;

    public SettingsController(SettingsInputBoundary settingsInputBoundary) {
        this.settingsInputBoundary = settingsInputBoundary;
    }

    public void prepareView() {
        settingsInputBoundary.prepareView();
    }

    public void execute(String amount, String category, String difficulty,
                        String type, String gamemode, String darkMode) {
        SettingsInputData settingsInputData = new SettingsInputData(amount, category,
                difficulty, type, gamemode, darkMode);
        settingsInputBoundary.execute(settingsInputData);
    }
}
