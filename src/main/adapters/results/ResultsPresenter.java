package main.adapters.results;

import main.adapters.start.StartViewModel;
import main.use_case.results.ResultsOutputBoundary;
import main.use_case.results.ResultsOutputData;
import main.view.ViewManager;

public class ResultsPresenter implements ResultsOutputBoundary {
    private final ResultsViewModel resultsViewModel;
    private final StartViewModel startViewModel;
    private final ViewManager viewManager;

    public ResultsPresenter(ResultsViewModel resultsViewModel,
                            StartViewModel startViewModel,
                            ViewManager viewManager) {
        this.resultsViewModel = resultsViewModel;
        this.startViewModel = startViewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareView(ResultsOutputData resultsOutputData) {
        ResultsState state = resultsViewModel.getState();
        state.setResults(resultsOutputData.getResults());
        state.setError(null);
        resultsViewModel.setState(state);
        resultsViewModel.firePropertyChanged();
        viewManager.setActiveView(resultsViewModel.getViewName());
    }

    @Override
    public void prepareFailView(String error) {
        ResultsState state = resultsViewModel.getState();
        state.setError(error);
        resultsViewModel.setState(state);
        resultsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView() {
        ResultsState state = resultsViewModel.getState();
        state.setError(null);
        resultsViewModel.setState(state);
        viewManager.setActiveView(startViewModel.getViewName());
    }
}
