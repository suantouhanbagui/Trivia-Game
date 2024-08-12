package main.adapters.two_player;

import main.adapters.play.PlayPresenter;
import main.adapters.start.StartViewModel;
import main.entities.Player;
import main.use_case.play.PlayOutputData;
import main.view.ViewManager;

public class TwoPlayerPresenter extends PlayPresenter {
    private final TwoPlayerViewModel twoPlayerViewModel;

    public TwoPlayerPresenter(TwoPlayerViewModel twoPlayerViewModel,
                              StartViewModel startViewModel,
                              ViewManager manager) {
        super(twoPlayerViewModel,
                startViewModel,
                manager);
        this.twoPlayerViewModel = twoPlayerViewModel;
    }

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
