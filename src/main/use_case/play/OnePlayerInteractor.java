package main.use_case.play;

import main.data_access.ResultRecordingDAO;
import main.data_access.TriviaDBInterface;
import main.entities.Player;
import main.entities.QuestionList;
import main.use_case.settings.SettingsInteractor;

import java.io.IOException;

public class OnePlayerInteractor extends PlayInteractor {
    private Player player = null;
    public OnePlayerInteractor(PlayOutputBoundary playOutputBoundary,
                               SettingsInteractor settingsInteractor,
                               TriviaDBInterface questionGenerator,
                               ResultRecordingDAO resultRecordingDAO) {
        super(playOutputBoundary,
                settingsInteractor,
                questionGenerator,
                resultRecordingDAO);
    }

    @Override
    public void prepareView() {
        try {
            // generate questions
            QuestionList creationSettings = settingsDTO.getCreationSettings();
            int amount = creationSettings.size();
            questionList = questionGenerator.getQuestions(amount,
                    creationSettings.getCategory(),
                    creationSettings.getDifficulty(),
                    creationSettings.getType());
            question = questionList.next();
            // create player
            String name = playOutputBoundary.gatherName("Enter your name:");
            if (name == null) {
                return;
            }
            player = new Player(name);
            // create output data
            Player[] players = new Player[]{player};
            PlayOutputData playOutputData = new PlayOutputData(amount,
                    1,
                    question,
                    players,
                    null,
                    null);
            playOutputBoundary.prepareView(playOutputData);
        } catch (IOException e) {
            // inform user of the error
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
        // increment score if the answer is correct
        String answer = playInputData.getAnswer();
        String correctAnswer = question.getCorrectAnswer();
        Boolean previousCorrect = answer.equals(correctAnswer);
        if (previousCorrect) {
            player.stepScore();
        }
        // get next question if possible and send output data
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
            // give feedback for the last question
            PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                    questionList.getIndex(),
                    question,
                    new Player[]{player},
                    previousCorrect,
                    correctAnswer);
            playOutputBoundary.prepareView(playOutputData);
            // present results to the user
            String message = player.getName() +
                    " scored " +
                    player.getScore() +
                    "/" +
                    questionList.size();
            playOutputBoundary.prepareSuccessView(message);
            // save results
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
                "/" +
                questionList.size() +
                "; Results: " +
                player.getName() +
                " scored " +
                player.getScore() +
                "/" +
                questionList.size();
        resultRecordingDAO.recordResult(results);
    }
}
