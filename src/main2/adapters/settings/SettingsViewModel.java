package main2.adapters.settings;

import main2.adapters.ViewModel;
import main2.entities.QuestionSettings;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsViewModel extends ViewModel {

    public SettingsViewModel() {
        super("settings");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
