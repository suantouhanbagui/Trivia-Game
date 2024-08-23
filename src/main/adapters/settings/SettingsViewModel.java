package main.adapters.settings;

import main.adapters.ViewModel;
import main.entities.QuestionSettings;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** {@code ViewModel} for the settings view. */
public class SettingsViewModel extends ViewModel {
    /** Instructions for the user regarding what to do on this screen. */
    public final String INSTRUCTIONS_LABEL = "Please select from the dropdowns:";

    /** Text to describe the amount field. */
    public final String AMOUNT_LABEL = "Amount of questions:";
    /** Text to describe the category dropdown. */
    public final String CATEGORY_LABEL = "Question category:";
    /** Text to describe the difficulty dropdown. */
    public final String DIFFICULTY_LABEL = "Question difficulty:";
    /** Text to describe the type dropdown. */
    public final String TYPE_LABEL = "Question type:";
    /** Text to describe the gamemode dropdown. */
    public final String GAMEMODE_LABEL = "Gamemode:";
    /** Text to describe the light/dark mode dropdown. */
    public final String TOGGLE_DARK_MODE_LABEL = "Themes:";

    /** Options for the category dropdown. */
    public final String[] CATEGORY_OPTIONS = QuestionSettings.getCategoryOptions();
    /** Options for the difficulty dropdown. */
    public final String[] DIFFICULTY_OPTIONS = QuestionSettings.getDifficultyOptions();
    /** Options for the type dropdown. */
    public final String[] TYPE_OPTIONS = QuestionSettings.getTypeOptions();
    /** Options for the gamemode dropdown. */
    public final String[] GAMEMODE_OPTIONS = new String[] {"Single player", "Two player"};
    /** Options for the light/dark mode dropdown. */
    public final String[] LIGHT_DARK_MODE_OPTIONS = new String[] {"Light Mode", "Dark Mode"};

    /** Text for the button that confirms setting changes. */
    public String CONFIRM_BUTTON_LABEL = "Confirm";

    /** Font to use in the view. */
    public Font FONT = new Font("Serif", Font.PLAIN, 20);

    /** Stores the state of the view. */
    private SettingsState state = new SettingsState();
    /** Used to alert the view that the state has changed. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Instantiate a new {@code SettingsViewModel}. */
    public SettingsViewModel() {
        super("settings");
    }

    /**
     * Getter for the state.
     *
     * @return the state.
     */
    public SettingsState getState() {
        return state;
    }

    /**
     * Setter for the state.
     *
     * @param state New value for the state.
     */
    public void setState(SettingsState state) {
        this.state = state;
    }

    /** Alert the view that the state has changed. */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    /**
     * Add a view to listen for changes to the state.
     *
     * @param listener A view object to listen for changes to the state.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
