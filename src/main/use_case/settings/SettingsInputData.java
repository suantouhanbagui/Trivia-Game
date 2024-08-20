package main.use_case.settings;

public class SettingsInputData {
    /** Amount of questions. */
    private final String amount;
    /** Selected category. */
    private final String category;
    /** Selected difficulty. */
    private final String difficulty;
    /** Selected type. */
    private final String type;
    /** Selected gamemode. */
    private final String gamemode;
    /** Selected light/dark mode setting. */
    private final String darkMode;

    /**
     * Instantiate a new {@code SettingsInputData} that stores settings
     * selected by the user.
     *
     * @param amount of questions.
     * @param category selected category.
     * @param difficulty selected difficulty.
     * @param type selected type.
     * @param gamemode selected gamemode.
     * @param darkMode selected light/dark mode setting.
     */
    public SettingsInputData(String amount, String category, String difficulty,
                             String type, String gamemode, String darkMode) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.gamemode = gamemode;
        this.darkMode = darkMode;
    }

    /**
     * Getter for amount of questions.
     *
     * @return amount.
     */
    public String getAmount() {
        return amount;
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
     * Getter for question difficulty.
     *
     * @return the selected difficulty.
     */
    public String getDifficulty() {
        return difficulty;
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
     * Getter for gamemode.
     *
     * @return the selected gamemode.
     */
    public String getGamemode() {
        return gamemode;
    }

    /**
     * Getter for dark mode.
     *
     * @return the current dark mode setting.
     */
    public String getDarkMode(){
        return darkMode;
    }

}