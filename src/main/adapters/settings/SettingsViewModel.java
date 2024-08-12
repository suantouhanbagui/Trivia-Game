package main.adapters.settings;

import main.adapters.ViewModel;
import main.entities.QuestionSettings;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsViewModel extends ViewModel {
    public String INSTRUCTIONS_LABEL = "Please select from the dropdowns:";

    public String AMOUNT_LABEL = "Amount of questions::";
    public String CATEGORY_LABEL = "Question category:";
    public String DIFFICULTY_LABEL = "Question difficulty:";
    public String TYPE_LABEL = "Question type:";
    public String GAMEMODE_LABEL = "Gamemode:";
    public String TOGGLE_DARK_MODE_LABEL = "Toggle Light/Dark Mode";

    public String[] CATEGORY_OPTIONS = QuestionSettings.getCategoryOptions();
    public String[] DIFFICULTY_OPTIONS = QuestionSettings.getDifficultyOptions();
    public String[] TYPE_OPTIONS = QuestionSettings.getTypeOptions();
    public String[] GAMEMODE_OPTIONS = new String[] {"Single player", "Two player"};
    public String[] LIGHT_DARK_MODE_OPTIONS = new String[] {"Light Mode", "Dark Mode"};

    public String CONFIRM_BUTTON_LABEL = "Confirm";

    public Font FONT = new Font("Serif", Font.PLAIN, 20);

    private SettingsState state = new SettingsState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SettingsViewModel() {
        super("settings");
    }

    public SettingsState getState() {
        return state;
    }

    public void setState(SettingsState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
