package main2.adapters.settings;

import main2.entities.QuestionSettings;

public class SettingsState {
    private String amount = "10";
    private String category = QuestionSettings.getCategoryOptions()[0];
    private String difficulty = QuestionSettings.getDifficultyOptions()[0];
    private String type = QuestionSettings.getDifficultyOptions()[0];
    private String gamemode = "Single player";
    private String darkMode = "Light Mode";

    private String error = null;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGamemode() {
        return gamemode;
    }

    public void setGamemode(String gamemode) {
        this.gamemode = gamemode;
    }

    public String getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(String darkMode) {
        this.darkMode = darkMode;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}