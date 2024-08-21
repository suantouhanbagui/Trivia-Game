package adapters.play;

import main.adapters.play.PlayPresenter;
import main.adapters.play.PlayState;
import main.adapters.play.PlayViewModel;
import main.adapters.start.StartState;
import main.adapters.start.StartViewModel;
import main.entities.Player;
import main.entities.Question;
import main.use_case.play.PlayOutputData;
import main.view.ViewManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PlayPresenterTest {
    private PlayViewModel playViewModel;
    private StartViewModel startViewModel;
    private final JFrame dummyFrame = new JFrame();
    private ViewManager viewManager;
    private PlayPresenter playPresenter;
    private final String questionText = "question text here";
    private final String correctAnswer = "correct answer here";
    private final ArrayList<String> incorrectAnswers = new ArrayList<>();
    private final String difficulty = "Any Difficulty";
    private final String category = "Any Category";
    private final String type = "Any Type";
    private Question dummyQuestion;
    private final Player[] playerList = {new Player("A"), new Player("B")};
    private PlayOutputData playOutputData;
    private final String dummyError = "some error here";

    @BeforeEach
    void setUp(){
        this.playViewModel = new PlayViewModel();
        this.startViewModel = new StartViewModel();
        this.viewManager = new ViewManager(dummyFrame);
        for (int i=1; i<4; i++){
            this.incorrectAnswers.add("incorrect ans");
        }
        this.dummyQuestion = new Question(questionText, correctAnswer, incorrectAnswers,
                difficulty, category, type);
        this.playOutputData = new PlayOutputData(10, 1, dummyQuestion,
                playerList, true, correctAnswer);
    }

    @Test
    void prepareViewCorrect() {
        this.playViewModel = new PlayViewModel(){
            @Override
            public void setState(PlayState state) {
                assertNull(this.getState().getError());
                assertEquals(this.getState().getFeedback(), "Correct!");
            }
        };
        ViewManager mockViewManager = mock(viewManager.getClass());
        this.playPresenter = new PlayPresenter(playViewModel, startViewModel, mockViewManager);
        playPresenter.prepareView(playOutputData);
        Mockito.verify(mockViewManager).setActiveView(playViewModel.getViewName());
    }

    @Test
    void prepareViewIncorrect() {
        this.playViewModel = new PlayViewModel(){
            @Override
            public void setState(PlayState state) {
                assertNull(this.getState().getError());
                assertTrue(this.getState().getFeedback().contains("Incorrect! "));
            }
        };
        ViewManager mockViewManager = mock(viewManager.getClass());
        this.playPresenter = new PlayPresenter(playViewModel, startViewModel, mockViewManager);
        this.playOutputData = new PlayOutputData(10, 1, dummyQuestion,
                playerList, false, correctAnswer);
        playPresenter.prepareView(playOutputData);
        Mockito.verify(mockViewManager).setActiveView(playViewModel.getViewName());
    }

    @Test
    void prepareSuccessView() {
        this.playViewModel = new PlayViewModel(){
            @Override
            public void setState(PlayState state) {
                assertNull(this.getState().getError());
                assertNull(this.getState().getFeedback());
            }
        };
        this.startViewModel = new StartViewModel(){
            @Override
            public void setState(StartState state) {
                assertEquals(dummyError, this.getState().getError());
            }
        };
        this.playPresenter = new PlayPresenter(playViewModel, startViewModel, viewManager);
        playPresenter.prepareSuccessView(dummyError);
    }

    @Test
    void prepareFailView() {
        this.startViewModel = new StartViewModel(){
            @Override
            public void setState(StartState state) {
                assertEquals(dummyError, this.getState().getError());
            }
        };
        ViewManager mockViewManager = mock(viewManager.getClass());
        this.playPresenter = new PlayPresenter(playViewModel, startViewModel, mockViewManager);
        playPresenter.prepareFailView(dummyError);
        Mockito.verify(mockViewManager).setActiveView(startViewModel.getViewName());
    }
}