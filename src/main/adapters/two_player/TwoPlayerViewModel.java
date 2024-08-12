package main.adapters.two_player;

import main.adapters.play.PlayState;
import main.adapters.play.PlayViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TwoPlayerViewModel extends PlayViewModel {
    private TwoPlayerState state = new TwoPlayerState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TwoPlayerViewModel() {
        super("two player");
    }

    @Override
    public TwoPlayerState getState() {
        return state;
    }

    @Override
    public void setState(PlayState state) {
        this.state = (TwoPlayerState) state;
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
