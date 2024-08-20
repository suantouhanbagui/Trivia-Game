package main.adapters.settings;

import main.entities.QuestionSettings;

/** Stores the state for a view object that allows selecting settings. */
public class SettingsState {
    /** Amount of questions. */
    private String amount = "10";
    /** Selected category. */
    private String category = QuestionSettings.getCategoryOptions()[0];
    /** Selected difficulty. */
    private String difficulty = QuestionSettings.getDifficultyOptions()[0];
    /** Selected type. */
    private String type = QuestionSettings.getDifficultyOptions()[0];
    /** Selected gamemode. */
    private String gamemode = "Single player";
    /** Selected light/dark mode setting. */
    private String darkMode = "Light Mode";

    /** Stores an error message to display to the user. */
    private String error = null;

    /**
     * Getter for amount of questions.
     *
     * @return amount.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Setter for amount of questions.
     *
     * @param amount of questions.
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Getter for question category.
     *
     * @return the category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for question category.
     *
     * @param category to generate questions from.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter for question difficulty.
     *
     * @return the selected difficulty.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Setter for question difficulty.
     *
     * @param difficulty new value for difficulty.
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Getter for question type.
     *
     * @return the selected type.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for question type.
     *
     * @param type new value for type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for gamemode.
     *
     * @return the selected gamemode.
     */
    public String getGamemode() {
        return gamemode;
    }

    /**
     * Setter for gamemode.
     *
     * @param gamemode new value for gamemode.
     */
    public void setGamemode(String gamemode) {
        this.gamemode = gamemode;
    }

    /**
     * Getter for dark mode.
     *
     * @return the current dark mode setting.
     */
    public String getDarkMode() {
        return darkMode;
    }

    /**
     * Setter for dark mode.
     *
     * @param darkMode mew value for dark mode.
     */
    public void setDarkMode(String darkMode) {
        this.darkMode = darkMode;
    }

    /**
     * Getter for the error message.
     *
     * @return the stored error message, or null if there is none.
     */
    public String getError() {
        return error;
    }

    /**
     * Set the error message.
     *
     * @param error new error message. Use null to indicate that there is no
     *        error message to display.
     */
    public void setError(String error) {
        this.error = error;
    }
}