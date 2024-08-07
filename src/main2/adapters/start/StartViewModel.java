package main2.adapters.start;

import main2.adapters.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** ViewModel for the start use case. */
public class StartViewModel extends ViewModel {
    public String TITLE_LABEL = "Trivia Quiz";

    public String START_BUTTON_LABEL = "Play";
    public String SETTINGS_BUTTON_LABEL = "Settings";
    public String RESULTS_BUTTON_LABEL = "Previous Results";
    public String EXIT_BUTTON_LABEL = "Exit";

    public Font TITLE_FONT = new Font("Serif", Font.BOLD | Font.ITALIC, 50);
    public Font BUTTON_FONT = new Font("Serif", Font.PLAIN, 20);

    private StartState state = new StartState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public StartViewModel() {
        super("start");
    }

    public StartState getState() {
        return state;
    }

    public void setState(StartState state) {
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
