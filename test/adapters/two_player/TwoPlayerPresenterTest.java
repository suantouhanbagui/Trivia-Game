package adapters.two_player;


import main.adapters.play.PlayState;
import main.adapters.start.StartViewModel;
import main.adapters.two_player.TwoPlayerPresenter;
import main.adapters.two_player.TwoPlayerViewModel;
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

class TwoPlayerPresenterTest {
    private TwoPlayerPresenter twoPlayerPresenter;
    private TwoPlayerViewModel twoPlayerViewModel;
    private StartViewModel startViewModel;
    private final JFrame dummyFrame = new JFrame();
    private ViewManager viewManager;
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
    void setUp() {
        twoPlayerViewModel = new TwoPlayerViewModel();
        startViewModel = new StartViewModel();
        viewManager = new ViewManager(dummyFrame);
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
        this.twoPlayerViewModel = new TwoPlayerViewModel(){
            @Override
            public void setState(PlayState state) {
                assertNull(this.getState().getError());
                assertEquals(this.getState().getFeedback(), "Correct!");
            }
        };
        ViewManager mockViewManager = mock(viewManager.getClass());
        this.twoPlayerPresenter = new TwoPlayerPresenter(twoPlayerViewModel, startViewModel, mockViewManager);
        twoPlayerPresenter.prepareView(playOutputData);
        Mockito.verify(mockViewManager).setActiveView(twoPlayerViewModel.getViewName());
    }

    @Test
    void prepareViewIncorrect() {
        this.twoPlayerViewModel = new TwoPlayerViewModel(){
            @Override
            public void setState(PlayState state) {
                assertNull(this.getState().getError());
                assertTrue(this.getState().getFeedback().contains("Incorrect! "));
            }
        };
        ViewManager mockViewManager = mock(viewManager.getClass());
        this.twoPlayerPresenter = new TwoPlayerPresenter(twoPlayerViewModel,
                startViewModel, mockViewManager);
        this.playOutputData = new PlayOutputData(10, 1, dummyQuestion,
                playerList, false, correctAnswer);
        twoPlayerPresenter.prepareView(playOutputData);
        Mockito.verify(mockViewManager).setActiveView(twoPlayerViewModel.getViewName());
    }
}