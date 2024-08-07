package main2.use_case.settings;

import main2.entities.QuestionList;

public class SettingsDTO {
    private final QuestionList creationSettings;
    private final String gamemode;

    public SettingsDTO(QuestionList creationSettings, String gamemode) {
        this.creationSettings = creationSettings;
        this.gamemode = gamemode;
    }

    public QuestionList getCreationSettings() {
        return creationSettings;
    }

    public String getGamemode() {
        return gamemode;
    }
}

