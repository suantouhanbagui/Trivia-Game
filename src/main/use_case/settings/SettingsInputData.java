package main.use_case.settings;

public class SettingsInputData {
    private final String amount;
    private final String category;
    private final String difficulty;
    private final String type;
    private final String gamemode;
    private final String darkMode;

    public SettingsInputData(String amount, String category, String difficulty,
                             String type, String gamemode, String darkMode) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.gamemode = gamemode;
        this.darkMode = darkMode;
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

    public String getGamemode() {
        return gamemode;
    }

    public String getDarkMode(){
        return darkMode;
    }

}