package main.use_case.settings;

public interface SettingsOutputBoundary {
    void prepareView();

    void prepareFailView(String error);

    void prepareSuccessView();
}
