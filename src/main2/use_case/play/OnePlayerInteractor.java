package main2.use_case.play;

import main2.data_access.ResultRecordingDAO;
import main2.data_access.TriviaDBInterface;
import main2.entities.Player;
import main2.entities.QuestionList;
import main2.use_case.settings.SettingsInteractor;

import java.io.IOException;

public class OnePlayerInteractor extends PlayInteractor {
    private Player player = null;
    public OnePlayerInteractor(PlayOutputBoundary playOutputBoundary,
                               SettingsInteractor settingsInteractor,
                               TriviaDBInterface questionGenerator,
                               ResultRecordingDAO resultRecordingDAO) {
        super(playOutputBoundary,settingsInteractor,questionGenerator,resultRecordingDAO);
    }

    @Override
    public void prepareView() {
        String name = playOutputBoundary.gatherName("Enter your name:");
        if (name == null) {
            return;
        }
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
            PlayOutputData playOutputData = new PlayOutputData(amount,
                    1,
                    question,
                    players,
                    null,
                    null);
            playOutputBoundary.prepareView(playOutputData);
        } catch (IOException e) {
            playOutputBoundary.prepareFailView(e.getMessage());
            reset();
        }
    }

    private void reset() {
        player = null;
        questionList = null;
        question = null;
    }

    @Override
    public void execute(PlayInputData playInputData) {
        String answer = playInputData.getAnswer();
        String correctAnswer = question.getCorrectAnswer();
        Boolean previousCorrect = answer.equals(correctAnswer);
        if (previousCorrect) {
            player.stepScore();
        }
        if (questionList.hasNext()) {
            question = questionList.next();
            PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                    questionList.getIndex(),
                    question,
                    new Player[]{player},
                    previousCorrect,
                    correctAnswer);
            playOutputBoundary.prepareView(playOutputData);
        } else {
            PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                    questionList.getIndex(),
                    question,
                    new Player[]{player},
                    previousCorrect,
                    correctAnswer);
            playOutputBoundary.prepareView(playOutputData);
            String message = player.getName() +
                    " scored " +
                    player.getScore() +
                    " points.";
            playOutputBoundary.prepareSuccessView(message);
            try {
                recordResult();
            } catch (IOException e) {
                playOutputBoundary.prepareFailView("Failed to record results of this game.");
            } finally {
                reset();
            }
        }
    }

    private void recordResult() throws IOException {
        String results = player.toString() +
                "; Results: " +
                player.getName() +
                " scored " +
                player.getScore();
        resultRecordingDAO.recordResult(results);
    }
}
