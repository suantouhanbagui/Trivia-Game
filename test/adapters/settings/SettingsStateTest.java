package adapters.settings;

import main.adapters.settings.SettingsState;
import main.entities.QuestionSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsStateTest {
    private SettingsState settingsState;

    // dummy data
    private final String amount = "20";
    private final String category = "Mythology";
    private final String difficulty = "Medium";
    private final String type = "Multiple Choice";
    private final String gamemode = "Two Player";
    private final String darkMode = "Dark Mode";
    private final String dummyError = "some error here";


    @BeforeEach
    void setUp() {
        this.settingsState = new SettingsState();
    }

    @Test
    void getAmount() {
        String actualAmount = this.settingsState.getAmount();
        assertEquals("10", actualAmount);
    }

    @Test
    void setAmount() {
        settingsState.setAmount(amount);
        String actualAmount = settingsState.getAmount();
        assertEquals(amount, actualAmount);
    }

    @Test
    void getCategory() {
        String actualCategory = this.settingsState.getCategory();
        assertEquals(QuestionSettings.getCategoryOptions()[0], actualCategory);
    }

    @Test
    void setCategory() {
        settingsState.setCategory(category);
        String actualCategory = settingsState.getCategory();
        assertEquals(category, actualCategory);
    }

    @Test
    void getDifficulty() {
        String actualDifficulty = this.settingsState.getDifficulty();
        assertEquals(QuestionSettings.getDifficultyOptions()[0], actualDifficulty);
    }

    @Test
    void setDifficulty() {
        settingsState.setDifficulty(difficulty);
        String actualDifficulty = settingsState.getDifficulty();
        assertEquals(difficulty, actualDifficulty);
    }

    @Test
    void getType() {
        String actualType = this.settingsState.getType();
        assertEquals(QuestionSettings.getDifficultyOptions()[0], actualType);
    }

    @Test
    void setType() {
        settingsState.setType(type);
        String actualType = settingsState.getType();
        assertEquals(type, actualType);
    }

    @Test
    void getGamemode() {
        String actualGamemode= this.settingsState.getGamemode();
        assertEquals("Single player", actualGamemode);
    }

    @Test
    void setGamemode() {
        settingsState.setGamemode(gamemode);
        String actualGamemode = settingsState.getGamemode();
        assertEquals(gamemode, actualGamemode);
    }

    @Test
    void getDarkMode() {
        String actualDarkMode = this.settingsState.getDarkMode();
        assertEquals("Light Mode", actualDarkMode);
    }

    @Test
    void setDarkMode() {
        settingsState.setDarkMode(darkMode);
        String actualDarkMode = settingsState.getDarkMode();
        assertEquals(darkMode, actualDarkMode);
    }

    @Test
    void getError() {
        String actualError = this.settingsState.getError();
        assertNull(actualError);
    }

    @Test
    void setError() {
        settingsState.setError(dummyError);
        String actualError = settingsState.getError();
        assertEquals(dummyError, actualError);
    }
}