package main.adapters.start;

import main.adapters.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** ViewModel for the start use case. */
public class StartViewModel extends ViewModel {
    /** Title of the game. */
    public String TITLE_LABEL = "Trivia Quiz";

    /** Text for the button that starts the game. */
    public String START_BUTTON_LABEL = "Play";
    /** Text for the button to go to the settings screen. */
    public String SETTINGS_BUTTON_LABEL = "Settings";
    /** Text for the button to view past results. */
    public String RESULTS_BUTTON_LABEL = "Previous Results";
    /** Text for the exit button. */
    public String EXIT_BUTTON_LABEL = "Exit";

    /** Font for the title text. */
    public Font TITLE_FONT = new Font("Serif", Font.BOLD | Font.ITALIC, 50);
    /** Font for the buttons. */
    public Font BUTTON_FONT = new Font("Serif", Font.PLAIN, 20);

    /** Stores the state of the view. */
    private StartState state = new StartState();
    /** Used to alert the view that the state has changed. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Instantiate a new {@code StartViewModel}. */
    public StartViewModel() {
        super("start");
    }

    /**
     * Getter for the state.
     *
     * @return the state.
     */
    public StartState getState() {
        return state;
    }

    /**
     * Setter for the state.
     *
     * @param state New value for the state.
     */
    public void setState(StartState state) {
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
