package main.adapters.two_player;

import main.adapters.play.PlayPresenter;
import main.adapters.start.StartViewModel;
import main.entities.Player;
import main.use_case.play.PlayOutputData;
import main.view.ViewManager;

/**
 * Presenter that communicates with {@code TwoPlayerViewModel} to update the
 * view with results from {@code TwoPlayerInteractor}.
 */
public class TwoPlayerPresenter extends PlayPresenter {
    /** View model for the two player gamemode screen. */
    private final TwoPlayerViewModel twoPlayerViewModel;

    /**
     * Instantiate a new {@code TwoPlayerPresenter}.
     *
     * @param twoPlayerViewModel - to be updated with new results.
     * @param startViewModel - view model for the start screen.
     * @param viewManager - manages which view is active.
     */
    public TwoPlayerPresenter(TwoPlayerViewModel twoPlayerViewModel,
                              StartViewModel startViewModel,
                              ViewManager viewManager) {
        super(twoPlayerViewModel,
                startViewModel,
                viewManager);
        this.twoPlayerViewModel = twoPlayerViewModel;
    }

    /**
     * Present user with feedback on the previous question if applicable, then
     * present the next question. Set the play view as the active view if not
     * already the case.
     *
     * @param playOutputData contains data to update the view with.
     */
    @Override
    public void prepareView(PlayOutputData playOutputData) {
        Player[] players = playOutputData.getPlayers();
        Player player1 = players[0];
        Player player2 = players[1];
        Player currentPlayer = players[(playOutputData.getIndex() - 1) % 2];

        TwoPlayerState state = twoPlayerViewModel.getState();
        String scores = player1.getName() +
                ": " +
                player1.getScore() +
                ", " +
                player2.getName() +
                ": " +
                player2.getScore();
        state.setScores(scores);
        state.setTurn(currentPlayer.getName() + "'s turn.");

        super.prepareView(playOutputData);
    }
}
