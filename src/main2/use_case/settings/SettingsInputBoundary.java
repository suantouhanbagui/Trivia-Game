package main2.use_case.settings;

public interface SettingsInputBoundary {
    void prepareView();

    void execute(SettingsInputData inputData);
}