package main.use_case.two_player;

import main.use_case.play.OnePlayerInteractor;
import main.use_case.play.PlayInputBoundary;
import main.use_case.play.PlayInputData;
import main.use_case.play.PlayInteractor;
import main.use_case.settings.SettingsDTO;
import main.use_case.settings.SettingsInteractor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Implementation of {@code PlayInputBoundary} that switches between a
 * {@code OnePlayerInteractor} and {@code TwoPlayerInteractor} to enable
 * changing the gamemode at runtime.
 */
public class PlayInteractorAdapter implements PlayInputBoundary, PropertyChangeListener {
    /** Use case interactor for the single player gamemode. */
    private final OnePlayerInteractor onePlayerInteractor;
    /** Use case interactor for the two player gamemode. */
    private final TwoPlayerInteractor twoPlayerInteractor;
    /** Use case interactor for the currently selected gamemode. */
    private PlayInteractor activeInteractor;

    /**
     * Instantiate a new {@code PlayInteractorAdapter}.
     *
     * @param onePlayerInteractor interactor for single player gamemode.
     * @param twoPlayerInteractor interactor for two player gamemode.
     * @param settingsInteractor interactor for changing settings. This will
     *        listen for changes to determine which gamemode is selected and
     *        which of the two interactors to use.
     */
    public PlayInteractorAdapter(OnePlayerInteractor onePlayerInteractor,
                                 TwoPlayerInteractor twoPlayerInteractor,
                                 SettingsInteractor settingsInteractor) {
        this.onePlayerInteractor = onePlayerInteractor;
        this.twoPlayerInteractor = twoPlayerInteractor;
        settingsInteractor.addPropertyChangeListener(this);
        // set single player as the default gamemode.
        activeInteractor = onePlayerInteractor;
    }

    /** Call {@code prepareView} on the active interactor. */
    @Override
    public void prepareView() {
        activeInteractor.prepareView();
    }

    /**
     * Call {@code execute} on the active interactor and pass in input data.
     *
     * @param inputData contains the user's answer to the question.
     */
    @Override
    public void execute(PlayInputData inputData) {
        activeInteractor.execute(inputData);
    }

    /**
     * Change the active interactor whenever the gamemode is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source and
     *        the property that has changed. {evt.getNewValue()} Must be
     *        castable to {@code settingsDTO}.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsDTO settingsDTO = (SettingsDTO) evt.getNewValue();
        if (settingsDTO.getGamemode().equals("Single player")) {
            activeInteractor = onePlayerInteractor;
        } else {
            activeInteractor = twoPlayerInteractor;
        }
    }
}