package main.adapters.results;

import main.adapters.start.StartViewModel;
import main.use_case.results.ResultsOutputBoundary;
import main.use_case.results.ResultsOutputData;
import main.view.ViewManager;

/** Presenter to display results of the results interactor. */
public class ResultsPresenter implements ResultsOutputBoundary {
    /** View model for the starting screen. */
    private final StartViewModel startViewModel;
    /** View model for the past results screen. */
    private final ResultsViewModel resultsViewModel;
    /** Manages which view is displayed to the user. */
    private final ViewManager viewManager;

    /**
     * Instantiate a new {@code SettingsPresenter}.
     *
     * @param resultsViewModel to be updated with new results.
     * @param startViewModel view model for the start screen.
     * @param viewManager manages which view is active.
     */
    public ResultsPresenter(ResultsViewModel resultsViewModel,
                            StartViewModel startViewModel,
                            ViewManager viewManager) {
        this.resultsViewModel = resultsViewModel;
        this.startViewModel = startViewModel;
        this.viewManager = viewManager;
    }

    /** Sets the results view as the active view. */
    @Override
    public void prepareView(ResultsOutputData resultsOutputData) {
        ResultsState state = resultsViewModel.getState();
        state.setResults(resultsOutputData.getResults());
        state.setError(null);
        resultsViewModel.setState(state);
        resultsViewModel.firePropertyChanged();
        viewManager.setActiveView(resultsViewModel.getViewName());
    }

    /**
     * Present an error message to the user indicating the results could not be
     * retrieved.
     *
     * @param error message that will be displayed to the user.
     */
    @Override
    public void prepareFailView(String error) {
        ResultsState state = resultsViewModel.getState();
        state.setError(error);
        resultsViewModel.setState(state);
        resultsViewModel.firePropertyChanged();
    }

    /**
     * Complete the interaction by returning to the start screen.
     */
    @Override
    public void prepareSuccessView() {
        ResultsState state = resultsViewModel.getState();
        state.setError(null);
        resultsViewModel.setState(state);
        viewManager.setActiveView(startViewModel.getViewName());
    }
}
