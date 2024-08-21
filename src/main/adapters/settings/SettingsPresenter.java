package main.adapters.settings;

import main.adapters.start.StartViewModel;
import main.use_case.settings.SettingsOutputBoundary;
import main.view.ViewManager;

/** Presenter to display results of the settings interactor. */
public class SettingsPresenter implements SettingsOutputBoundary {
    /** View model for the starting screen. */
    private final StartViewModel startViewModel;
    /** View model for the setting selection screen. */
    private final SettingsViewModel settingsViewModel;
    /** Manages which view is displayed to the user. */
    private final ViewManager viewManager;

    /**
     * Instantiate a new {@code SettingsPresenter}.
     *
     * @param settingsViewModel to be updated with new results.
     * @param startViewModel view model for the start screen.
     * @param viewManager manages which view is active.
     */
    public SettingsPresenter(SettingsViewModel settingsViewModel,
                             StartViewModel startViewModel,
                             ViewManager viewManager) {
        this.settingsViewModel = settingsViewModel;
        this.startViewModel = startViewModel;
        this.viewManager = viewManager;
    }

    /** Sets the settings view as the active view. */
    @Override
    public void prepareView() {
        viewManager.setActiveView(settingsViewModel.getViewName());
    }

    /**
     * Present an error message to the user indicating the settings could not
     * be saved without changing the active screen.
     *
     * @param error message that will be displayed to the user.
     */
    @Override
    public void prepareFailView(String error) {
        SettingsState state = settingsViewModel.getState();
        state.setError(error);
        settingsViewModel.setState(state);
        settingsViewModel.firePropertyChanged();
    }

    /**
     * Complete the interaction by returning to the start screen.
     */
    @Override
    public void prepareSuccessView() {
        // reset error message for settingsViewModel
        SettingsState settingsState = settingsViewModel.getState();
        settingsState.setError(null);
        settingsViewModel.setState(settingsState);
        // switch back to start view
        viewManager.setActiveView(startViewModel.getViewName());
    }
}
