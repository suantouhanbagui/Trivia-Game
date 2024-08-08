package main2.adapters.play;

import main2.adapters.start.StartState;
import main2.adapters.start.StartViewModel;
import main2.entities.Player;
import main2.entities.Question;
import main2.use_case.play.PlayOutputBoundary;
import main2.use_case.play.PlayOutputData;
import main2.view.ViewManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class PlayPresenter implements PlayOutputBoundary {
    private final PlayViewModel playViewModel;
    private final StartViewModel startViewModel;
    private final ViewManager viewManager;

    public PlayPresenter(PlayViewModel playViewModel,
                         StartViewModel startViewModel,
                         ViewManager viewManager) {
        this.playViewModel = playViewModel;
        this.startViewModel = startViewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareView(PlayOutputData playOutputData) {
        Question nextQuestion = playOutputData.getNextQuestion();
        ArrayList<String> options = new ArrayList<>(nextQuestion.getIncorrectAnswers());
        options.add(nextQuestion.getCorrectAnswer());
        Collections.shuffle(options);

        PlayState state = playViewModel.getState();

        state.setProgress("Question " + String.valueOf(playOutputData.getIndex()) + " of " + String.valueOf(playOutputData.getAmount()));
        state.setText("<html>" + nextQuestion.getQuestionText() + "</html>");
        state.setOptions(options.toArray(new String[4]));
        if (playOutputData.getPreviousCorrect() != null) {
            if (playOutputData.getPreviousCorrect()) {
                state.setFeedback("Correct!");
            } else {
                state.setFeedback("Incorrect! The answer was \"" + playOutputData.getPreviousAnswer() + "\".");
            }
        }
        state.setError(null);
        playViewModel.setState(state);
        playViewModel.firePropertyChanged();
        viewManager.setActiveView(playViewModel.getViewName());
    }

    @Override
    public void prepareSuccessView(PlayOutputData playOutputData) {
        PlayState state = playViewModel.getState();
        state.setFeedback(null);
        state.setError(null);
        viewManager.setActiveView(startViewModel.getViewName());
        Player player = playOutputData.getPlayers()[0];
        StartState startState = startViewModel.getState();
        startState.setError(player.getName() + " scored " + String.valueOf(player.getScore()) + " points.");
        startViewModel.setState(startState);
        startViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        PlayState state = playViewModel.getState();
        state.setError(error);
        playViewModel.setState(state);
        playViewModel.firePropertyChanged();
        viewManager.setActiveView(startViewModel.getViewName());
    }

    @Override
    public String gatherName(String message) {
        return JOptionPane.showInputDialog(message);
    }
}
