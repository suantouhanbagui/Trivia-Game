package main.adapters.two_player;

import main.adapters.play.PlayState;
import main.adapters.play.PlayViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** {@code ViewModel} for the two player gamemode view. */
public class TwoPlayerViewModel extends PlayViewModel {
    /** Stores the state of the view. */
    private TwoPlayerState state = new TwoPlayerState();
    /** Used to alert the view that the state has changed. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Instantiate a {@code TwoPlayerViewModel}. */
    public TwoPlayerViewModel() {
        super("two player");
    }

    /**
     * Getter for the state.
     *
     * @return the state.
     */
    @Override
    public TwoPlayerState getState() {
        return state;
    }

    /** Setter for the state.
     *
     * @param state New value for the state.
     */
    @Override
    public void setState(PlayState state) {
        this.state = (TwoPlayerState) state;
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
