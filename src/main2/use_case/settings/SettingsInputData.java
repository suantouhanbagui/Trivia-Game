package main2.use_case.settings;

public class SettingsInputData {
    private final String amount;
    private final String category;
    private final String difficulty;
    private final String type;
    private final String gamemode;

    public SettingsInputData(String amount, String category, String difficulty,
                             String type, String gamemode) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.gamemode = gamemode;
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

}