package use_case.two_player;

import main.data_access.ResultRecordingDAO;
import main.data_access.TriviaDBInterface;
import main.entities.Question;
import main.entities.QuestionList;
import main.use_case.play.OnePlayerInteractor;
import main.use_case.play.PlayInputData;
import main.use_case.play.PlayOutputBoundary;
import main.use_case.play.PlayOutputData;
import main.use_case.settings.SettingsDTO;
import main.use_case.settings.SettingsInteractor;
import main.use_case.settings.SettingsOutputBoundary;
import main.use_case.two_player.PlayInteractorAdapter;
import main.use_case.two_player.TwoPlayerInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayInteractorAdapterTest {
    private OnePlayerInteractor onePlayerInteractor;
    private TwoPlayerInteractor twoPlayerInteractor;
    private PlayInteractorAdapter playInteractorAdapter;
    private TestPlayOutputBoundary playOutputBoundary;
    private TestTriviaDBInterface triviaDBInterface;
    private TestResultRecordingDAO resultRecordingDAO;
    private TestSettingsInteractor settingsInteractor;
    private TestPlayInputData testPlayInputData;

    @BeforeEach
    void setUp() {
        playOutputBoundary = new TestPlayOutputBoundary();
        triviaDBInterface = new TestTriviaDBInterface();
        resultRecordingDAO = new TestResultRecordingDAO();
        settingsInteractor = new TestSettingsInteractor();

        QuestionList questionList = new QuestionList();
        SettingsDTO settingsDTO = new SettingsDTO(questionList, "Single player");
        settingsInteractor.setSettingsDTO(settingsDTO);

        onePlayerInteractor = new OnePlayerInteractor(playOutputBoundary, settingsInteractor, triviaDBInterface, resultRecordingDAO);
        twoPlayerInteractor = new TwoPlayerInteractor(playOutputBoundary, settingsInteractor, triviaDBInterface, resultRecordingDAO);

        playInteractorAdapter = new PlayInteractorAdapter(onePlayerInteractor, twoPlayerInteractor, settingsInteractor);
    }

    @Test
    void prepareView() {
        QuestionList questionList = new QuestionList();
        questionList.addQuestion(new Question("Sample Question", "Sample Answer", new ArrayList<>(), "Easy", "General", "Multiple Choice"));
        triviaDBInterface.setQuestions(questionList);
        playOutputBoundary.setName("Player1");

        // Act
        playInteractorAdapter.prepareView();

        // Assert
        assertNotNull(playOutputBoundary.getPlayOutputData());
        assertEquals("Player1", playOutputBoundary.getPlayerName());
    }

    @Test
    void excute() {

    }

    // Test implementations copied from the singlePlayerInteractorTest

    private static class TestPlayOutputBoundary implements PlayOutputBoundary {
        private PlayOutputData playOutputData;
        private String name;
        private String failMessage;
        private String successMessage;
        private boolean successViewPrepared = false;
        private boolean failViewPrepared = false;

        @Override
        public void prepareView(PlayOutputData playOutputData) {
            this.playOutputData = playOutputData;
        }

        @Override
        public void prepareFailView(String error) {
            this.failMessage = error;
            failViewPrepared = true;
        }

        @Override
        public void prepareSuccessView(String message) {
            this.successMessage = message;
            successViewPrepared = true;
        }

        @Override
        public String gatherName(String message) {
            return this.name; // Return predefined name for testing
        }

        public PlayOutputData getPlayOutputData() {
            return playOutputData;
        }

        public String getPlayerName() {
            return name;
        }

        public String getFailMessage() {
            return failMessage;
        }

        public String getSuccessMessage() {
            return successMessage;
        }

        public boolean isSuccessViewPrepared() {
            return successViewPrepared;
        }

        public boolean isFailViewPrepared() {
            return failViewPrepared;
        }

        public void setName(String name) {
            this.name = name;
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
            super((SettingsOutputBoundary) new PlayInteractorAdapterTest.TestSettingsOutputBoundary());
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