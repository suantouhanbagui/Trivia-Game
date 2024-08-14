package main.use_case.settings;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import main.entities.QuestionList;
import org.jetbrains.annotations.NotNull;

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
            QuestionList creationSettings = getCreationSettings(inputData);
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
                settingsOutputBoundary.prepareFailView("Questions per player must be an integer between 1 and 50 inclusive.");
            } else {
                settingsOutputBoundary.prepareFailView(e.getMessage());
            }
        }
    }

    private static @NotNull QuestionList getCreationSettings(SettingsInputData inputData) throws IllegalArgumentException {
        int amount = Integer.parseInt(inputData.getAmount());
        if (amount < 1 | amount > 50) {
            throw new NumberFormatException();
        } else if ("Two player".equals(inputData.getGamemode()) & amount % 2 != 0) {
            throw new IllegalArgumentException("Amount of questions must be even for two player mode.");
        }
        return new QuestionList(amount,
                inputData.getCategory(),
                inputData.getDifficulty(),
                inputData.getType());
    }
}
