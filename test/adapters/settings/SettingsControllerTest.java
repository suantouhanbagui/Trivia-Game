package adapters.settings;


import main.adapters.settings.SettingsController;
import main.adapters.settings.SettingsPresenter;
import main.adapters.settings.SettingsViewModel;
import main.adapters.start.StartViewModel;
import main.use_case.settings.SettingsInputBoundary;
import main.use_case.settings.SettingsInteractor;
import main.use_case.settings.SettingsOutputBoundary;
import main.view.ViewManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.swing.*;

import static org.mockito.Mockito.mock;


class SettingsControllerTest {
    private SettingsInputBoundary settingsInputBoundary;
    private SettingsOutputBoundary settingsOutputBoundary;
    private SettingsViewModel settingsViewModel;
    private StartViewModel startViewModel;
    private ViewManager viewManager;
    private final JFrame dummyFrame = new JFrame();
    private SettingsController settingsController;

    // dummy settings
    private final String amount = "10";
    private final String category = "General Knowledge";
    private final String difficulty = "Easy";
    private final String type = "Multiple Choice";
    private final String gamemode = "Single Player";
    private final String darkMode = "Light Mode";


    @BeforeEach
    void setUp() {
        this.settingsViewModel = new SettingsViewModel();
        this.startViewModel = new StartViewModel();
        this.viewManager = new ViewManager(dummyFrame);
        this.settingsOutputBoundary = new SettingsPresenter(settingsViewModel,
                startViewModel, viewManager);
    }

    @Test
    void prepareView() {
        this.settingsInputBoundary = new SettingsInteractor(settingsOutputBoundary);
        SettingsInputBoundary mockSettingsInteractor = mock(settingsInputBoundary.getClass());
        this.settingsController = new SettingsController(mockSettingsInteractor);
        settingsController.prepareView();
        Mockito.verify(mockSettingsInteractor).prepareView();
    }

    @Test
    void execute() {
        this.settingsInputBoundary = new SettingsInteractor(settingsOutputBoundary);
        SettingsInputBoundary mockSettingsInteractor = mock(settingsInputBoundary.getClass());
        this.settingsController = new SettingsController(mockSettingsInteractor);
        settingsController.execute(amount, category, difficulty, type, gamemode, darkMode);
        Mockito.verify(mockSettingsInteractor).execute(ArgumentMatchers.any());
    }
}