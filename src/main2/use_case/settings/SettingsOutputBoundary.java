package main2.use_case.settings;

public interface SettingsOutputBoundary {
    void prepareView();

    void prepareFailView(String error);

    void prepareSuccessView();
}
