package adapters.results;

import main.adapters.results.ResultsPresenter;
import main.adapters.results.ResultsState;
import main.adapters.results.ResultsViewModel;
import main.adapters.start.StartViewModel;
import main.use_case.results.ResultsOutputData;
import main.view.ViewManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ResultsPresenterTest {
    private ResultsViewModel resultsViewModel;
    private StartViewModel startViewModel;
    private final JFrame dummyFrame = new JFrame();
    private ViewManager viewManager;
    private ResultsPresenter resultsPresenter;
    private String dummyResults = "some results here";
    private String dummyError = "some error here";
    private ResultsOutputData resultsOutputData;

    @BeforeEach
    void setUp() {
        this.resultsOutputData = new ResultsOutputData(dummyResults);
        this.resultsViewModel = new ResultsViewModel();
        this.startViewModel = new StartViewModel();
        this.viewManager = new ViewManager(dummyFrame);
        this.resultsPresenter = new ResultsPresenter(resultsViewModel, startViewModel, viewManager);
    }

    @Test
    void prepareView() {
        this.viewManager = new ViewManager(dummyFrame){
            public void setActiveView(String viewName) {
                // test if method is called
                assert true;
                ResultsState state = resultsViewModel.getState();
                String actualResults = state.getResults();
                // test that correct results and error set in state
                assertEquals(dummyResults, actualResults);
                assertNull(state.getError());
            }
        };
        resultsPresenter.prepareView(resultsOutputData);
    }

    @Test
    void prepareFailView() {
        resultsPresenter.prepareFailView(dummyError);
        // test if correct error set
        assertEquals(dummyError, resultsViewModel.getState().getError());
    }

    @Test
    void prepareSuccessView() {
        resultsPresenter.prepareSuccessView();
        // test if error is null
        assertNull(resultsViewModel.getState().getError());
    }
}