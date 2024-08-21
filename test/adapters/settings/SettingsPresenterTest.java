package adapters.settings;

import main.adapters.settings.SettingsPresenter;
import main.adapters.settings.SettingsViewModel;
import main.adapters.start.StartViewModel;
import main.view.ViewManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SettingsPresenterTest {
    private SettingsViewModel settingsViewModel;
    private StartViewModel startViewModel;
    private final JFrame dummyFrame = new JFrame();
    private ViewManager viewManager;
    private SettingsPresenter settingsPresenter;
    private final String dummyError = "some error here";


    @BeforeEach
    void setUp() {
        this.settingsViewModel = new SettingsViewModel();
        this.startViewModel = new StartViewModel();
        this.viewManager = new ViewManager(dummyFrame);
    }

    @Test
    void prepareView() {
        ViewManager mockViewManager = mock(viewManager.getClass());
        this.settingsPresenter = new SettingsPresenter(settingsViewModel,
                startViewModel, mockViewManager);
        settingsPresenter.prepareView();
        Mockito.verify(mockViewManager).setActiveView(ArgumentMatchers.any());
    }

    @Test
    void prepareFailView() {
        this.settingsPresenter = new SettingsPresenter(settingsViewModel,
                startViewModel, viewManager);
        settingsPresenter.prepareFailView(dummyError);
        // test if correct error set
        assertEquals(dummyError, settingsViewModel.getState().getError());
    }

    @Test
    void prepareSuccessView() {
        this.settingsPresenter = new SettingsPresenter(settingsViewModel,
                startViewModel, viewManager);
        settingsPresenter.prepareSuccessView();
        // test if error is null
        assertNull(settingsViewModel.getState().getError());
    }
}