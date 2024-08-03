package main.entities;

public class Settings {
    private boolean darkModeEnabled;
    private String gameMode;
    private int qAmount;
    private String qCategory;
    private String qDifficulty;
    private String qType;

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

    public void setDarkModeEnabled(boolean darkModeEnabled) {
        this.darkModeEnabled = darkModeEnabled;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setqAmount(int qAmount) {
        this.qAmount = qAmount;
    }

    public void setqCategory(String qCategory) {
        this.qCategory = qCategory;
    }

    public void setqDifficulty(String qDifficulty) {
        this.qDifficulty = qDifficulty;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }
}