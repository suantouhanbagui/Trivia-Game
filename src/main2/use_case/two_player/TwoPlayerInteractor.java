package main2.use_case.two_player;

import main2.data_access.ResultRecordingDAO;
import main2.data_access.TriviaDBInterface;
import main2.entities.Player;
import main2.entities.Question;
import main2.entities.QuestionList;
import main2.use_case.play.PlayInputData;
import main2.use_case.play.PlayInteractor;
import main2.use_case.play.PlayOutputBoundary;
import main2.use_case.play.PlayOutputData;
import main2.use_case.settings.SettingsInteractor;

import java.io.IOException;

public class TwoPlayerInteractor extends PlayInteractor {
    private final PlayOutputBoundary playOutputBoundary;
    private final TriviaDBInterface questionGenerator;
    private final ResultRecordingDAO resultRecordingDAO;

    private Player[] players = new Player[2];
    private QuestionList questionList;
    private Question question;

    public TwoPlayerInteractor(PlayOutputBoundary playOutputBoundary,
                               SettingsInteractor settingsInteractor,
                               TriviaDBInterface questionGenerator,
                               ResultRecordingDAO resultRecordingDAO) {
        super(playOutputBoundary,
                settingsInteractor,
                questionGenerator,
                resultRecordingDAO);
        this.playOutputBoundary = playOutputBoundary;
        this.questionGenerator = questionGenerator;
        settingsInteractor.addPropertyChangeListener(this);
        settingsInteractor.firePropertyChanged();
        this.resultRecordingDAO = resultRecordingDAO;
    }

    @Override
    public void prepareView() {
        String name;
        for (int i = 1; i <= 2; i++) {
            name = playOutputBoundary.gatherName("Enter the name of player " + i + ":");
            if (name == null) {
                return;
            }
            players[i - 1] = new Player(name);
        }

        QuestionList creationSettings = settingsDTO.getCreationSettings();
        int amount = creationSettings.size() * 2;
        try {
            questionList = questionGenerator.getQuestions(amount,
                    creationSettings.getCategory(),
                    creationSettings.getDifficulty(),
                    creationSettings.getType());
            question = questionList.next();
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
        players = new Player[2];
        questionList = null;
        question = null;
    }

    @Override
    public void execute(PlayInputData playInputData) {
        String answer = playInputData.getAnswer();
        String correctAnswer = question.getCorrectAnswer();
        Boolean previousCorrect = answer.equals(correctAnswer);
        if (previousCorrect) {
            players[(questionList.getIndex() - 1) % 2].stepScore();
        }
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
            PlayOutputData playOutputData = new PlayOutputData(questionList.size(),
                    questionList.getIndex(),
                    question,
                    players,
                    previousCorrect,
                    correctAnswer);
            playOutputBoundary.prepareView(playOutputData);
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
        results.append(players[0].getName())
                .append(": ")
                .append(players[0].getScore())
                .append("; ")
                .append(players[1].getName())
                .append(": ")
                .append(players[1].getScore())
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
