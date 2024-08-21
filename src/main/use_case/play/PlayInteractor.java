package main.use_case.play;

import main.data_access.ResultRecordingDAO;
import main.data_access.TriviaDBInterface;
import main.entities.Question;
import main.entities.QuestionList;
import main.use_case.settings.SettingsDTO;
import main.use_case.settings.SettingsInteractor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** Abstract class for the gameplay interactors. */
public abstract class PlayInteractor implements PlayInputBoundary, PropertyChangeListener {
    /** Presenter for interacting with the view model. */
    protected final PlayOutputBoundary playOutputBoundary;
    /** DAO for generating questions from the API. */
    protected final TriviaDBInterface questionGenerator;
    /** DAO for recording the results of a game. */
    protected final ResultRecordingDAO resultRecordingDAO;

    /** Questions for the current quiz. */
    protected QuestionList questionList;
    /** The current question. */
    protected Question question;
    /** Contains settings for a new game. */
    protected SettingsDTO settingsDTO;

    /**
     * Instantiate a new {@code PlayInteractor}.
     *
     * @param playOutputBoundary the presenter that interacts with the view
     *        model to display results to the user.
     * @param settingsInteractor observe for changes to the settings.
     * @param questionGenerator DAO for generating questions from the API.
     * @param resultRecordingDAO DAO for recording the results of a game.
     */
    public PlayInteractor(PlayOutputBoundary playOutputBoundary,
                          SettingsInteractor settingsInteractor,
                          TriviaDBInterface questionGenerator,
                          ResultRecordingDAO resultRecordingDAO) {
        this.playOutputBoundary = playOutputBoundary;
        settingsInteractor.addPropertyChangeListener(this);
        settingsInteractor.firePropertyChanged();
        this.questionGenerator = questionGenerator;
        this.resultRecordingDAO = resultRecordingDAO;
    }

    /**
     * Called whenever the settings are changed in the settings interactor.
     * Update {@code settingsDTO} to the new one from the event.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *        and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        settingsDTO = (SettingsDTO) evt.getNewValue();
    }
}
