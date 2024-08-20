package main.use_case.settings;

import main.entities.QuestionList;

/** Read-only object storing settings chosen by the user. */
public class SettingsDTO {
    /** Empty {@code QuestionList} that stores settings for generating questions. */
    private final QuestionList creationSettings;
    /** Gamemode chosen by the user. */
    private final String gamemode;

    /**
     * Instantiate a new {@code SettingsDTO}.
     *
     * @param creationSettings for generating questions.
     * @param gamemode selected by the user.
     */
    public SettingsDTO(QuestionList creationSettings, String gamemode) {
        this.creationSettings = creationSettings;
        this.gamemode = gamemode;
    }

    /**
     * Getter for {@code creationSettings}.
     *
     * @return a {@code QuestionList} storing settings for generating questions
     */
    public QuestionList getCreationSettings() {
        return creationSettings;
    }

    /**
     * Getter for {@code gamemode}.
     *
     * @return the gamemode selected by the user.
     */
    public String getGamemode() {
        return gamemode;
    }
}

