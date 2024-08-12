package main.adapters.results;

import main.adapters.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ResultsViewModel extends ViewModel {
    public String TITLE_LABEL = "Previous game results";
    public String BACK_BUTTON_LABEL = "Back";

    public Font FONT = new Font("Serif", Font.PLAIN, 20);

    private ResultsState state = new ResultsState();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ResultsViewModel() {
        super("results");
    }

    public ResultsState getState() {
        return state;
    }

    public void setState(ResultsState state) {
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
