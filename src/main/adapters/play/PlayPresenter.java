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

public class PlayPresenter implements PlayOutputBoundary {
    private final PlayViewModel playViewModel;
    private final StartViewModel startViewModel;
    private final ViewManager manager;

    public PlayPresenter(PlayViewModel playViewModel,
                         StartViewModel startViewModel,
                         ViewManager manager) {
        this.playViewModel = playViewModel;
        this.startViewModel = startViewModel;
        this.manager = manager;
    }

    @Override
    public void prepareView(PlayOutputData playOutputData) {
        Question nextQuestion = playOutputData.getNextQuestion();
        ArrayList<String> options = new ArrayList<>(nextQuestion.getIncorrectAnswers());
        options.add(nextQuestion.getCorrectAnswer());
        Collections.shuffle(options);

        PlayState state = playViewModel.getState();
        String progress = "Question " +
                playOutputData.getIndex() +
                " of " +
                playOutputData.getAmount();
        state.setProgress(progress);
        state.setText("<html>" + nextQuestion.getQuestionText() + "</html>");
        state.setOptions(options.toArray(new String[4]));
        if (playOutputData.getPreviousCorrect() != null) {
            if (playOutputData.getPreviousCorrect()) {
                state.setFeedback("Correct!");
            } else {
                state.setFeedback("Incorrect! The answer was \"" +
                        playOutputData.getPreviousAnswer() +
                        "\".");
            }
        }
        state.setError(null);
        playViewModel.setState(state);
        playViewModel.firePropertyChanged();
        manager.setActiveView(playViewModel.getViewName());
    }

    @Override
    public void prepareSuccessView(String message) {
        PlayState state = playViewModel.getState();
        state.setError(null);
        state.setFeedback(null);
        playViewModel.setState(state);
        manager.setActiveView(startViewModel.getViewName());
        StartState startState = startViewModel.getState();
        startState.setError(message);
        startViewModel.setState(startState);
        startViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        StartState state = startViewModel.getState();
        state.setError(error);
        startViewModel.setState(state);
        manager.setActiveView(startViewModel.getViewName());
        startViewModel.firePropertyChanged();
    }

    @Override
    public String gatherName(String message) {
        return JOptionPane.showInputDialog(message);
    }
}
