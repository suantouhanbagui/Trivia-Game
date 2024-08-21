package main.adapters.play;

import main.adapters.start.StartState;
import main.adapters.start.StartViewModel;
import main.entities.Question;
import main.use_case.play.PlayOutputBoundary;
import main.use_case.play.PlayOutputData;
import main.view.ViewManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/** Presenter to display results of the play interactor. */
public class PlayPresenter implements PlayOutputBoundary {
    /** View model for the starting screen. */
    private final StartViewModel startViewModel;
    /** View model for the gameplay screen. */
    private final PlayViewModel playViewModel;
    /** Manages which view is displayed to the user. */
    private final ViewManager viewManager;

    /**
     * Instantiate a new {@code PlayPresenter}.
     *
     * @param playViewModel to be updated with new results.
     * @param startViewModel view model for the start screen.
     * @param viewManager manages which view is active.
     */
    public PlayPresenter(PlayViewModel playViewModel,
                         StartViewModel startViewModel,
                         ViewManager viewManager) {
        this.playViewModel = playViewModel;
        this.startViewModel = startViewModel;
        this.viewManager = viewManager;
    }

    /**
     * Present user with feedback on the previous question if applicable, then
     * present the next question. Set the play view as the active view if not
     * already the case.
     *
     * @param playOutputData contains data to update the view with.
     */
    @Override
    public void prepareView(PlayOutputData playOutputData) {
        PlayState state = playViewModel.getState();
        // update progress
        String progress = "Question " +
                playOutputData.getIndex() +
                " of " +
                playOutputData.getAmount();
        state.setProgress(progress);
        // update question text
        Question nextQuestion = playOutputData.getNextQuestion();
        state.setText("<html>" + nextQuestion.getQuestionText() + "</html>");
        // update with options for the mew question
        ArrayList<String> options = new ArrayList<>(nextQuestion.getIncorrectAnswers());
        options.add(nextQuestion.getCorrectAnswer());
        Collections.shuffle(options);
        state.setOptions(options.toArray(new String[4]));
        // add feedback if applicable
        if (playOutputData.getPreviousCorrect() != null) {
            if (playOutputData.getPreviousCorrect()) {
                state.setFeedback("Correct!");
            } else {
                state.setFeedback("Incorrect! The answer was \"" +
                        playOutputData.getPreviousAnswer() +
                        "\".");
            }
        }
        // reset error message
        state.setError(null);
        // alert the view
        playViewModel.setState(state);
        playViewModel.firePropertyChanged();
        viewManager.setActiveView(playViewModel.getViewName());
    }

    /**
     * Return to the start view and present an error message indicating
     * something has gone wrong.
     *
     * @param error message that will be displayed to the user.
     */
    @Override
    public void prepareFailView(String error) {
        StartState state = startViewModel.getState();
        state.setError(error);
        startViewModel.setState(state);
        viewManager.setActiveView(startViewModel.getViewName());
        startViewModel.firePropertyChanged();
    }

    /**
     * Remove feedback and error message from the playViewModel. Switch to the
     * start view and present the user with a message.
     *
     * @param message to present the user with.
     */
    @Override
    public void prepareSuccessView(String message) {
        PlayState state = playViewModel.getState();
        state.setError(null);
        state.setFeedback(null);
        playViewModel.setState(state);
        viewManager.setActiveView(startViewModel.getViewName());
        StartState startState = startViewModel.getState();
        startState.setError(message);
        startViewModel.setState(startState);
        startViewModel.firePropertyChanged();
    }

    /**
     * Prompt the user to enter a nickname and return the name. Returns null if
     * no name was entered.
     *
     * @param message for the prompt.
     * @return name entered by the user, or null if nothing was entered.
     */
    @Override
    public String gatherName(String message) {
        return JOptionPane.showInputDialog(message);
    }
}
