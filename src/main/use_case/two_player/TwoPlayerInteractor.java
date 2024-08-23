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

/** Interactor for the two player use case. */
public class TwoPlayerInteractor extends PlayInteractor {
    /** Stores data for the two players. */
    private Player[] players = new Player[2];

    /**
     * Instantiate a new {@code TwoPlayerInteractor}.
     *
     * @param playOutputBoundary the presenter that interacts with the view
     *        model to display results to the user.
     * @param settingsInteractor observe for changes to the settings.
     * @param questionGenerator DAO for generating questions from the API.
     * @param resultRecordingDAO DAO for recording the results of a game.
     */
    public TwoPlayerInteractor(PlayOutputBoundary playOutputBoundary,
                               SettingsInteractor settingsInteractor,
                               TriviaDBInterface questionGenerator,
                               ResultRecordingDAO resultRecordingDAO) {
        super(playOutputBoundary,
                settingsInteractor,
                questionGenerator,
                resultRecordingDAO);
    }

    /**
     * Start a new game, then set the view associated with the gameplay use
     * case as the active view.
     */
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

    /**
     * Reset the player, questionList and question so the interactor is ready
     * to start a new game.
     */
    private void reset() {
        players = new Player[2];
        questionList = null;
        question = null;
    }

    /**
     * Present a feedback message that tells the user if their answer is
     * correct, then load the next question. If there are no questions left,
     * go back to the starting screen and display the results of the game
     * instead of loading a new question.
     *
     * @param inputData contains the user's answer to the question.
     */
    @Override
    public void execute(PlayInputData inputData) {
        // increment current player's score if the answer is correct
        String answer = inputData.getAnswer();
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

    /**
     * Use {@code resultRecordingDAO} from the superclass to record the results
     * of the game.
     *
     * @throws IOException when the DAO fails to record the results.
     */
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
