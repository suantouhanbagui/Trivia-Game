package main2.use_case.settings;

public class SettingsInputData {
    private boolean darkModeEnabled;
    private final String gamemode;
    private final String amount;
    private final String category;
    private final String difficulty;
    private final String type;

    public SettingsInputData(boolean darkModeEnabled, String gamemode, String amount,
                             String category, String difficulty, String type) {
        this.darkModeEnabled = darkModeEnabled;
        this.gamemode = gamemode;
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
    }

    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public String getGamemode() {
        return gamemode;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

}