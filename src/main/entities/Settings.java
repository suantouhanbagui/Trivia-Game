package main.entities;

public class Settings {
    private final boolean darkModeEnabled;
    private final String gameMode;
    private final int qAmount;
    private final String qCategory;
    private final String qDifficulty;
    private final String qType;

    public Settings() {
        darkModeEnabled = false;
        gameMode = "Single Player";
        qAmount = 10;
        qCategory = "Any Category";
        qDifficulty = "Any Difficulty";
        qType = "Any Type";
    }

    public Settings(boolean darkModeEnabled, String gameMode, int qAmount,
                    String qCategory, String qDifficulty, String qType) {
        this.darkModeEnabled = darkModeEnabled;
        this.gameMode = gameMode;
        this.qAmount = qAmount;
        this.qCategory = qCategory;
        this.qDifficulty = qDifficulty;
        this.qType = qType;
    }

    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public String getGameMode() {
        return gameMode;
    }

    public int getAmount() {
        return qAmount;
    }

    public String getCategory() {
        return qCategory;
    }

    public String getDifficulty() {
        return qDifficulty;
    }

    public String getType() {
        return qType;
    }
}