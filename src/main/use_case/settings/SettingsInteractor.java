package main.use_case.settings;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import main.entities.QuestionList;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** Interactor for the settings use case. */
public class SettingsInteractor implements SettingsInputBoundary {
    /** Presenter for interacting with the view model. */
    private final SettingsOutputBoundary settingsOutputBoundary;
    /** Stores settings chosen by the user. */
    private SettingsDTO settingsDTO = new SettingsDTO(new QuestionList(), "Single player");
    /** Alerts the gameplay interactors when settings are changed. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Instantiate a new {@code SettingsInteractor}.
     *
     * @param settingsOutputBoundary the presenter that interacts with the view
     *        model to display results to the user.
     */
    public SettingsInteractor(SettingsOutputBoundary settingsOutputBoundary) {
        this.settingsOutputBoundary = settingsOutputBoundary;
    }

    /** Alert all observing {@code PlayInteractor}s that settings have changed. */
    public void firePropertyChanged() {
        support.firePropertyChange("settingsDTO", null, settingsDTO);
    }

    /**
     * Set a {@code PlayInteractor} to listen for changes to the settings.
     *
     * @param listener the {@code PlayInteractor} that will listen for changes.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /** Set the view associated with the settings use case as the active view. */
    @Override
    public void prepareView() {
        settingsOutputBoundary.prepareView();
    }

    /**
     * Changes theme and alerts the {@code PlayInteractor}s of the setting
     * changes.
     *
     * @param inputData contains all settings selected by the user.
     */
    @Override
    public void execute(SettingsInputData inputData) {
        try {
            // change theme
            if (inputData.getDarkMode().equals("Dark Mode")) {
                FlatDarculaLaf.setup();
                FlatDarculaLaf.updateUI();
            } else {
                FlatIntelliJLaf.setup();
                FlatIntelliJLaf.updateUI();
            }
            // update the settingsDTO and alert PlayInteractors
            QuestionList creationSettings = getCreationSettings(inputData);
            settingsDTO = new SettingsDTO(creationSettings, inputData.getGamemode());
            firePropertyChanged();
            settingsOutputBoundary.prepareSuccessView();
        } catch (IllegalArgumentException e) {
            // alert user if the amount of questions is invalid
            if (e instanceof NumberFormatException) {
                settingsOutputBoundary.prepareFailView("Questions per player must be an integer between 1 and 50 inclusive.");
            } else {
                settingsOutputBoundary.prepareFailView(e.getMessage());
            }
        }
    }

    /**
     * Instantiate and return a {@code QuestionList} using the settings from
     * the input data.
     *
     * @param inputData contains settings selected by user.
     * @return a {@code QuestionList} with relevant settings from the
     *         {@code inputData}.
     * @throws IllegalArgumentException when the amount is invalid.
     */
    private static @NotNull QuestionList getCreationSettings(SettingsInputData inputData) throws IllegalArgumentException {
        int amount = Integer.parseInt(inputData.getAmount());
        // throw IllegalArgumentException if amount is invalid
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
