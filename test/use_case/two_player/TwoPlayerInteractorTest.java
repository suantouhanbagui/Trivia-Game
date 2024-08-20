package use_case.two_player;

import main.entities.Player;
import main.entities.Question;
import main.entities.QuestionList;
import main.use_case.play.PlayInputBoundary;
import main.use_case.play.PlayInputData;
import main.use_case.play.PlayOutputData;
import main.use_case.settings.SettingsDTO;
import main.use_case.settings.SettingsOutputBoundary;
import main.use_case.two_player.TwoPlayerInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.use_case.play.PlayOutputBoundary;
import main.use_case.settings.SettingsInteractor;
import main.data_access.TriviaDBInterface;
import main.data_access.ResultRecordingDAO;


import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TwoPlayerInteractorTest {
    private TestPlayOutputBoundary playOutputBoundary;
    private TestSettingsInteractor settingsInteractor;
    private TestTriviaDBInterface triviaDBInterface;
    private TestResultRecordingDAO resultRecordingDAO;
    private TwoPlayerInteractor twoPlayerInteractor;
    private TestPlayInputData testPlayInputData;

    @BeforeEach
    void setUp() {
        playOutputBoundary = new TestPlayOutputBoundary();
        settingsInteractor = new TestSettingsInteractor();
        triviaDBInterface = new TestTriviaDBInterface();
        resultRecordingDAO = new TestResultRecordingDAO();

        // Create a sample settingsDTO
        QuestionList questionList = new QuestionList();
        SettingsDTO settingsDTO = new SettingsDTO(questionList, "Two Player");
        settingsInteractor.setSettingsDTO(settingsDTO);

        twoPlayerInteractor = new TwoPlayerInteractor(playOutputBoundary, settingsInteractor,
                triviaDBInterface, resultRecordingDAO);
    }
    @Test
    void testPrepareViewSuccessful() {
        QuestionList questionList = new QuestionList();
        questionList.addQuestion(new Question("Sample Question", "Sample Answer", new ArrayList<>(), "Easy", "General", "Multiple Choice"));
        triviaDBInterface.setQuestions(questionList);
        String[] names = {"Player1", "Player2"};
        playOutputBoundary.setNames(names);

        twoPlayerInteractor.prepareView();

        assertNotNull(playOutputBoundary.getPlayOutputData());
        assertEquals(names, playOutputBoundary.getPlayerNames());

    }

    @Test
    void testPrepareViewIOException() {
        triviaDBInterface.setThrowException(true);

        twoPlayerInteractor.prepareView();

        assertNotNull(playOutputBoundary.getFailMessage());
    }

    @Test
    void execute() {

    }

    // Test implementations copied and modified from the singlePlayerInteractorTest

    private static class TestPlayOutputBoundary implements PlayOutputBoundary {
        private PlayOutputData playOutputData;
        private String[] names;
        private String failMessage;
        private String successMessage;

        @Override
        public void prepareView(PlayOutputData playOutputData) {
            this.playOutputData = playOutputData;
        }

        @Override
        public void prepareFailView(String error) {
            this.failMessage = error;
        }

        @Override
        public void prepareSuccessView(String message) {
            this.successMessage = message;
        }

        @Override
        public String gatherName(String message) {
            return this.names[0]; // Return predefined name for testing
        }

        public PlayOutputData getPlayOutputData() {
            return playOutputData;
        }

        public String[] getPlayerNames() {
            return names;
        }

        public String getFailMessage() {
            return failMessage;
        }

        public String getSuccessMessage() {
            return successMessage;
        }

        public void setNames(String[] names) {
            this.names = names;
        }
    }

    private static class TestTriviaDBInterface implements TriviaDBInterface {
        private QuestionList questions;
        private boolean throwException;

        @Override
        public QuestionList getQuestions(int amount, String category, String difficulty, String type) throws IOException {
            if (throwException) {
                throw new IOException("Database error");
            }
            return questions;
        }

        public void setQuestions(QuestionList questions) {
            this.questions = questions;
        }

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }
    }

    private static class TestResultRecordingDAO extends ResultRecordingDAO {
        private boolean resultRecorded = false;

        @Override
        public void recordResult(String result) {
            this.resultRecorded = true;
        }

        public boolean isResultRecorded() {
            return resultRecorded;
        }
    }

    private static class TestSettingsInteractor extends SettingsInteractor {
        private SettingsDTO settingsDTO;

        public TestSettingsInteractor() {
            super((SettingsOutputBoundary) new TwoPlayerInteractorTest.TestSettingsOutputBoundary());
        }

        @Override
        public SettingsDTO getSettingsDTO() {
            return settingsDTO;
        }

        public void setSettingsDTO(SettingsDTO settingsDTO) {
            this.settingsDTO = settingsDTO;
        }
    }

    private static class TestSettingsOutputBoundary implements SettingsOutputBoundary {
        @Override
        public void prepareView() {
            // No-op for testing
        }

        @Override
        public void prepareFailView(String error) {
            // No-op for testing
        }

        @Override
        public void prepareSuccessView() {
            // No-op for testing
        }
    }

    private static class TestPlayInputData extends PlayInputData {

        public TestPlayInputData(String answer) {
            super(answer);
        }

        public String getAnswer() {
            return super.getAnswer();
        }
    }
}