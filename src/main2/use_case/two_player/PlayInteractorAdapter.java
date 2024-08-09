package main2.use_case.two_player;

import main2.use_case.play.PlayInputBoundary;
import main2.use_case.play.PlayInputData;
import main2.use_case.play.PlayInteractor;
import main2.use_case.settings.SettingsDTO;
import main2.use_case.settings.SettingsInteractor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayInteractorAdapter implements PlayInputBoundary, PropertyChangeListener {
    private final PlayInteractor playInteractor;
    private final PlayInteractor twoPlayerInteractor;
    private PlayInteractor activeInteractor;


    public PlayInteractorAdapter(PlayInteractor playInteractor,
                                 PlayInteractor twoPlayerInteractor,
                                 SettingsInteractor settingsInteractor) {
        this.playInteractor = playInteractor;
        this.twoPlayerInteractor = twoPlayerInteractor;
        settingsInteractor.addPropertyChangeListener(this);

        activeInteractor = playInteractor;
    }

    @Override
    public void prepareView() {
        activeInteractor.prepareView();
    }

    @Override
    public void execute(PlayInputData playInputData) {
        activeInteractor.execute(playInputData);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsDTO settingsDTO = (SettingsDTO) evt.getNewValue();
        if (settingsDTO.getGamemode().equals("Single player")) {
            activeInteractor = playInteractor;
        } else {
            activeInteractor = twoPlayerInteractor;
        }
    }
}
