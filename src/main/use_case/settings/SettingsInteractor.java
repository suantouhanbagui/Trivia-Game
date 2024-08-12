package main.use_case.settings;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import main.entities.QuestionList;

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
            if (amount < 10 | amount > 50) {
                throw new NumberFormatException();
            } else if (inputData.getGamemode() == "Two player" & amount % 2 != 0) {
                throw new IllegalArgumentException("Amount of questions must be even for two player mode.");
            }
            QuestionList creationSettings = new QuestionList(amount,
                    inputData.getCategory(),
                    inputData.getDifficulty(),
                    inputData.getType());
            settingsDTO = new SettingsDTO(creationSettings, inputData.getGamemode());

            if (inputData.getDarkMode().equals("Dark Mode")) {
                FlatDarculaLaf.setup();
                FlatDarculaLaf.updateUI();
            } else {
                FlatIntelliJLaf.setup();
                FlatIntelliJLaf.updateUI();
            }
            firePropertyChanged();
            settingsOutputBoundary.prepareSuccessView();
        } catch (IllegalArgumentException e) {
            if (e instanceof NumberFormatException) {
                settingsOutputBoundary.prepareFailView("Questions per player must be an integer between 10 and 50 inclusive.");
            } else {
                settingsOutputBoundary.prepareFailView(e.getMessage());
            }
        }
    }
}
