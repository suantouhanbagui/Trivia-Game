package use_case.settings;

import main.use_case.settings.SettingsOutputBoundary;

public class SettingsOutputBoundaryTest implements SettingsOutputBoundary {
    private boolean viewPrepared;
    private String failMessage;
    private boolean successViewPrepared;

    public SettingsOutputBoundaryTest() {
        reset();
    }

    @Override
    public void prepareView() {
        this.viewPrepared = true;
    }

    @Override
    public void prepareFailView(String error) {
        this.failMessage = error;
    }

    @Override
    public void prepareSuccessView() {
        this.successViewPrepared = true;
    }

    // Accessor methods for testing
    public boolean isViewPrepared() {
        return viewPrepared;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public boolean isSuccessViewPrepared() {
        return successViewPrepared;
    }

    // Method to reset state between tests
    public void reset() {
        this.viewPrepared = false;
        this.failMessage = null;
        this.successViewPrepared = false;
    }
}
