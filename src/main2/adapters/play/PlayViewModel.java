package main.adapters.play;

import main.adapters.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayViewModel extends ViewModel {
    public Font FONT = new Font("Serif", Font.PLAIN, 20);

    private PlayState state = new PlayState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PlayViewModel() {
        super("play");
    }

    public PlayState getState() {
        return state;
    }

    public void setState(PlayState state) {
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
