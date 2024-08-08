package main2.use_case.play;

import main2.data_access.ResultRecordingDAO;
import main2.data_access.TriviaDBInterface;
import main2.entities.Player;
import main2.entities.Question;
import main2.entities.QuestionList;
import main2.use_case.settings.SettingsDTO;
import main2.use_case.settings.SettingsInteractor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class PlayInteractor implements PlayInputBoundary, PropertyChangeListener {
    private final PlayOutputBoundary playOutputBoundary;
    private final SettingsInteractor settingsInteractor;
    private final TriviaDBInterface questionGenerator;
    private final ResultRecordingDAO resultRecordingDAO;

    private Player player = null;
    private QuestionList questionList;
    private Question question;
    SettingsDTO settingsDTO;

    public PlayInteractor(PlayOutputBoundary playOutputBoundary,
                          SettingsInteractor settingsInteractor,
                          TriviaDBInterface questionGenerator,
                          ResultRecordingDAO resultRetrievalDAO) {
        this.playOutputBoundary = playOutputBoundary;
        this.settingsInteractor = settingsInteractor;
        settingsInteractor.addPropertyChangeListener(this);
        settingsInteractor.firePropertyChanged();
        this.questionGenerator = questionGenerator;
        this.resultRecordingDAO = resultRetrievalDAO;
    }

    @Override
    public void execute(PlayInputData playInputData) {
        String answer = playInputData.getAnswer();
        String previousAnswer = question.getCorrectAnswer();
        Boolean previousCorrect = answer.equals(previousAnswer);
        if (previousCorrect) {
            player.stepScore();
        }
        if (questionList.hasNext()) {
            question = questionList.next();
            PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                    questionList.getIndex(), question, new Player[]{player}, previousCorrect,
                    previousAnswer);
            playOutputBoundary.prepareView(playOutputData);
        } else {
            try {
                PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                        questionList.getIndex(), question, new Player[]{player}, previousCorrect,
                        previousAnswer);
                StringBuilder results  = new StringBuilder();
                results.append(player.toString())
                        .append("; Results: ")
                        .append(player.getName())
                        .append(" scored ")
                        .append(player.getScore());
                resultRecordingDAO.recordResult(results.toString());
                playOutputBoundary.prepareSuccessView(playOutputData);
            } catch (IOException e) {
                playOutputBoundary.prepareFailView("Failed to record results of this game.");
            } finally {
                reset();
            }
        }
    }

    @Override
    public void prepareView() {
        String name = playOutputBoundary.gatherName("Enter your name:");
        // TODO: make sure name is not empty string
        if (name != null) {
            player = new Player(name);
            QuestionList creationSettings = settingsDTO.getCreationSettings();
            int amount = creationSettings.size();
            try {
                questionList = questionGenerator.getQuestions(amount,
                        creationSettings.getCategory(),
                        creationSettings.getDifficulty(),
                        creationSettings.getType());
                question = questionList.next();
                Player[] players = new Player[]{player};
                PlayOutputData playOutputData = new PlayOutputData(amount, 1,
                        question, players, null, null);
                playOutputBoundary.prepareView(playOutputData);
            } catch (IOException e) {
                playOutputBoundary.prepareFailView(e.getMessage());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        settingsDTO = (SettingsDTO) evt.getNewValue();
    }

    private void reset() {
        player = null;
        questionList = null;
        question = null;
    }
}
