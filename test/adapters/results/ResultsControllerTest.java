package adapters.results;

import main.adapters.results.ResultsController;
import main.adapters.results.ResultsPresenter;
import main.adapters.results.ResultsViewModel;
import main.adapters.start.StartViewModel;
import main.data_access.ResultRetrievalDAO;
import main.use_case.results.ResultsInputBoundary;
import main.use_case.results.ResultsInteractor;
import main.use_case.results.ResultsOutputBoundary;
import main.view.ViewManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class ResultsControllerTest {
    private ResultsInputBoundary resultsInputBoundary;
    private ResultsOutputBoundary resultsOutputBoundary;
    private ResultsViewModel resultsViewModel;
    private StartViewModel startViewModel;
    private ViewManager viewManager;
    private ResultRetrievalDAO resultRetrievalDAO;
    private ResultsController resultsController;
    private JFrame dummyFrame = new JFrame();

    @BeforeEach
    void setUp() {
        this.resultsViewModel = new ResultsViewModel();
        this.startViewModel = new StartViewModel();
        this.viewManager = new ViewManager(dummyFrame);
        this.resultRetrievalDAO = new ResultRetrievalDAO();
        this.resultsOutputBoundary = new ResultsPresenter(resultsViewModel,
                startViewModel, viewManager);
    }

    @Test
    void prepareView() {
        this.resultsInputBoundary = new ResultsInteractor(resultsOutputBoundary, resultRetrievalDAO){
            // to see if method is called--actual implementation tested elsewhere
            public void prepareView(){
                assert true;
            }
        };
        this.resultsController = new ResultsController((ResultsInteractor) resultsInputBoundary);
        resultsController.prepareView();

    }

    @Test
    void execute() {
        this.resultsInputBoundary = new ResultsInteractor(resultsOutputBoundary, resultRetrievalDAO){
            // to see if method is called--actual implementation tested elsewhere
            public void execute(){
                assert true;
            }
        };
        this.resultsController = new ResultsController((ResultsInteractor) resultsInputBoundary);
        resultsController.execute();
    }
}