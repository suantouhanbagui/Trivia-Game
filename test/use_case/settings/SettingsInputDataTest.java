package use_case.settings;

import main.use_case.settings.SettingsInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsInputDataTest {
    //dummy data
    private String amount = "10";
    private String category = "General Knowledge";
    private String difficulty = "Easy";
    private String type = "Multiple Choice";
    private String gamemode = "Single Player";
    private String darkMode = "Light Mode";

    private SettingsInputData settingsInputData;

    @BeforeEach
    void setUp() {
        this.settingsInputData = new SettingsInputData(amount, category, difficulty,
                type, gamemode, darkMode);
    }

    @Test
    void getAmount() {
        assertEquals(settingsInputData.getAmount(), amount);
    }

    @Test
    void getCategory() {
        assertEquals(settingsInputData.getCategory(), category);
    }

    @Test
    void getDifficulty() {
        assertEquals(settingsInputData.getDifficulty(), difficulty);
    }

    @Test
    void getType() {
        assertEquals(settingsInputData.getType(), type);
    }

    @Test
    void getGamemode() {
        assertEquals(settingsInputData.getGamemode(), gamemode);
    }

    @Test
    void getDarkMode() {
        assertEquals(settingsInputData.getDarkMode(), darkMode);
    }
}