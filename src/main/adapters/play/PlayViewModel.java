package main.adapters.play;

import main.adapters.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** {@code ViewModel} for the gameplay view. */
public class PlayViewModel extends ViewModel {
    /** Font to use in the view. */
    public Font FONT = new Font("Serif", Font.PLAIN, 20);

    /** Stores the state of the view. */
    private PlayState state = new PlayState();
    /** Used to alert the view that the state has changed. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Instantiate a new {@code PlayViewModel}. */
    public PlayViewModel() {
        super("play");
    }

    /**
     * Instantiate a new PlayViewModel with a different name.
     *
     * @param viewName for this view model.
     */
    public PlayViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Getter for the state.
     *
     * @return the state.
     */
    public PlayState getState() {
        return state;
    }

    /** Setter for the state.
     *
     * @param state New value for the state.
     */
    public void setState(PlayState state) {
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
