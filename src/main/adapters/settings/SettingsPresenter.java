package main.adapters.settings;

import main.adapters.start.StartViewModel;
import main.use_case.settings.SettingsOutputBoundary;
import main.view.ViewManager;

public class SettingsPresenter implements SettingsOutputBoundary {
    private final StartViewModel startViewModel;
    private final SettingsViewModel settingsViewModel;
    private final ViewManager viewManager;

    public SettingsPresenter(SettingsViewModel settingsViewModel,
                             StartViewModel startViewModel,
                             ViewManager viewManager) {
        this.settingsViewModel = settingsViewModel;
        this.startViewModel = startViewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareView() {
        viewManager.setActiveView(settingsViewModel.getViewName());
    }

    @Override
    public void prepareFailView(String error) {
        SettingsState state = settingsViewModel.getState();
        state.setError(error);
        settingsViewModel.setState(state);
        settingsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView() {
        SettingsState state = settingsViewModel.getState();
        state.setError(null);
        settingsViewModel.setState(state);
        viewManager.setActiveView(startViewModel.getViewName());
    }
}