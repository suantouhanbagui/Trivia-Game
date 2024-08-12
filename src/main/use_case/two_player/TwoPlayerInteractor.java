package main.use_case.two_player;

import main.data_access.ResultRecordingDAO;
import main.data_access.TriviaDBInterface;
import main.entities.Player;
import main.entities.QuestionList;
import main.use_case.play.PlayInputData;
import main.use_case.play.PlayInteractor;
import main.use_case.play.PlayOutputBoundary;
import main.use_case.play.PlayOutputData;
import main.use_case.settings.SettingsInteractor;

import java.io.IOException;

public class TwoPlayerInteractor extends PlayInteractor {
    private Player[] players = new Player[2];

    public TwoPlayerInteractor(PlayOutputBoundary playOutputBoundary,
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
        QuestionList creationSettings = settingsDTO.getCreationSettings();
        int amount = creationSettings.size();
        try {
            // generate questions
            questionList = questionGenerator.getQuestions(amount,
                    creationSettings.getCategory(),
                    creationSettings.getDifficulty(),
                    creationSettings.getType());
            question = questionList.next();
            // create players
            String name;
            for (int i = 1; i <= 2; i++) {
                name = playOutputBoundary.gatherName("Enter the name of player " + i + ":");
                if (name == null) {
                    return;
                }
                players[i - 1] = new Player(name);
            }
            // create output data
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
        players = new Player[2];
        questionList = null;
        question = null;
    }

    @Override
    public void execute(PlayInputData playInputData) {
        // increment current player's score if the answer is correct
        String answer = playInputData.getAnswer();
        String correctAnswer = question.getCorrectAnswer();
        Boolean previousCorrect = answer.equals(correctAnswer);
        if (previousCorrect) {
            players[(questionList.getIndex() - 1) % 2].stepScore();
        }
        // get next question if possible and send output data
        if (questionList.hasNext()) {
            question = questionList.next();
            PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                    questionList.getIndex(),
                    question,
                    players,
                    previousCorrect,
                    correctAnswer);
            playOutputBoundary.prepareView(playOutputData);
        } else {
            // give feedback for the last question
            PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                    questionList.getIndex(),
                    question,
                    players,
                    previousCorrect,
                    correctAnswer);
            playOutputBoundary.prepareView(playOutputData);
            // present results to the user
            String message;
            int temp = players[0].compareTo(players[1]);
            if (temp > 0) {
                message = players[0].getName() + " wins!";
            } else if (temp < 0) {
                message = players[0].getName() + " wins!";
            } else {
                message = "It's a tie!";
            }
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
        StringBuilder results = new StringBuilder();
        results.append(players[0].toString())
                .append("/")
                .append(questionList.size() / 2)
                .append("; ")
                .append(players[1].toString())
                .append("/")
                .append(questionList.size() / 2)
                .append("; Result: ");
        int temp = players[0].compareTo(players[1]);
        if (temp > 0) {
            results.append(players[0].getName())
                    .append(" wins!");
        } else if (temp < 0) {
            results.append(players[1].getName())
                    .append(" wins!");
        } else {
            results.append("Tie");
        }
        resultRecordingDAO.recordResult(results.toString());
    }
}
