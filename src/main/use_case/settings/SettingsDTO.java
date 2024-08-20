package main.use_case.settings;

import main.entities.QuestionList;

public class SettingsDTO {
    private final QuestionList creationSettings;
    private String gamemode;

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

    public void setGamemode(String twoPlayer) {
        this.gamemode = twoPlayer;
    }
}

