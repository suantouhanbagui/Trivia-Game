package use_case.settings;

import main.entities.QuestionList;
import main.use_case.settings.SettingsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsDTOTest {
    private QuestionList creationSettings;
    private String gamemode;
    private SettingsDTO settingsDTO;

    @BeforeEach
    void setUp() {
        this.creationSettings = new QuestionList();
        this.gamemode = "Single Player";
        this.settingsDTO = new SettingsDTO(creationSettings, gamemode);
    }

    @Test
    void getCreationSettings() {
        QuestionList actual = settingsDTO.getCreationSettings();
        assertEquals(this.creationSettings, actual);
    }

    @Test
    void getGamemode() {
        String actual = settingsDTO.getGamemode();
        assertEquals(this.gamemode, actual);
    }

    @Test
    void setGamemode() {
        settingsDTO.setGamemode("Two Player");
        String actual = settingsDTO.getGamemode();
        assertEquals("Two Player", actual);
    }
}