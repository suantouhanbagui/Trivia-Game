package main.adapters.results;

import main.adapters.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** {@code ViewModel} for the results view. */
public class ResultsViewModel extends ViewModel {
    /** Text for the title of the screen. */
    public String TITLE_LABEL = "Previous game results";
    /** Text for the back button. */
    public String BACK_BUTTON_LABEL = "Back";

    /** Font to use in the view. */
    public Font FONT = new Font("Serif", Font.PLAIN, 20);

    /** Stores the state of the view. */
    private ResultsState state = new ResultsState();
    /** Used to alert the view that the state has changed. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Instantiate a new {@code ResultsViewModel}. */
    public ResultsViewModel() {
        super("results");
    }

    /**
     * Getter for the state.
     *
     * @return the state.
     */
    public ResultsState getState() {
        return state;
    }

    /**
     * Setter for the state.
     *
     * @param state New value for the state.
     */
    public void setState(ResultsState state) {
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
