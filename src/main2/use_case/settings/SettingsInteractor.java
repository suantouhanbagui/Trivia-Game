package main2.use_case.settings;

import main2.entities.QuestionList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsInteractor implements SettingsInputBoundary {
    private final SettingsOutputBoundary settingsOutputBoundary;

    private SettingsDTO settingsDTO = new SettingsDTO(new QuestionList(), "Single player");
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SettingsInteractor(SettingsOutputBoundary settingsOutputBoundary) {
        this.settingsOutputBoundary = settingsOutputBoundary;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("settingsDTO", null, settingsDTO);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void prepareView() {
        settingsOutputBoundary.prepareView();
    }

    @Override
    public void execute(SettingsInputData inputData) {
        try {
            int amount = Integer.parseInt(inputData.getAmount());
            if (amount < 10 | amount > 25) {
                throw new NumberFormatException();
            }
            QuestionList creationSettings = new QuestionList(amount,
                    inputData.getCategory(),
                    inputData.getDifficulty(),
                    inputData.getType());
            settingsDTO = new SettingsDTO(creationSettings, inputData.getGamemode());
            firePropertyChanged();
            settingsOutputBoundary.prepareSuccessView();
        } catch (IllegalArgumentException e) {
            if (e instanceof NumberFormatException) {
                settingsOutputBoundary.prepareFailView("Questions per player must be between 10 and 25 inclusive.");
            } else {
                settingsOutputBoundary.prepareFailView(e.getMessage());
            }
        }
    }
}
