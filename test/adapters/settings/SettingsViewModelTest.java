package adapters.settings;

import main.adapters.settings.SettingsState;
import main.adapters.settings.SettingsViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsViewModelTest {
    private SettingsViewModel settingsViewModel;
    private SettingsState testSettingsState;

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
        this.settingsViewModel = new SettingsViewModel();
        this.testSettingsState = new SettingsState();
    }

    @Test
    void getState() {
        SettingsState expectedState = new SettingsState();
        SettingsState actualState = settingsViewModel.getState();
        assertEquals(expectedState.getAmount(), actualState.getAmount());
        assertEquals(expectedState.getCategory(), actualState.getCategory());
        assertEquals(expectedState.getDifficulty(), actualState.getDifficulty());
        assertEquals(expectedState.getType(), actualState.getType());
        assertEquals(expectedState.getGamemode(), actualState.getGamemode());
        assertEquals(expectedState.getDarkMode(), actualState.getDarkMode());
        assertNull(actualState.getError());
    }

    @Test
    void setState() {
        testSettingsState.setAmount(amount);
        testSettingsState.setCategory(category);
        testSettingsState.setType(type);
        testSettingsState.setDifficulty(difficulty);
        testSettingsState.setGamemode(gamemode);
        testSettingsState.setDarkMode(darkMode);
        testSettingsState.setError(dummyError);

        settingsViewModel.setState(testSettingsState);

        SettingsState expectedState = testSettingsState;
        SettingsState actualState = settingsViewModel.getState();
        assertEquals(expectedState.getAmount(), actualState.getAmount());
        assertEquals(expectedState.getCategory(), actualState.getCategory());
        assertEquals(expectedState.getDifficulty(), actualState.getDifficulty());
        assertEquals(expectedState.getType(), actualState.getType());
        assertEquals(expectedState.getGamemode(), actualState.getGamemode());
        assertEquals(expectedState.getDarkMode(), actualState.getDarkMode());
        assertEquals(expectedState.getError(), actualState.getError());
    }
}