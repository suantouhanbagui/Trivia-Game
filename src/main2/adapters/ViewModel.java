package main2.adapters;

import java.beans.PropertyChangeListener;

/** NOTE: the code was taken directly from Paul Gries LoginCleanArchitecture. */
public abstract class ViewModel {

    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
