package main2.adapters.two_player;

import main2.adapters.play.PlayPresenter;
import main2.adapters.start.StartViewModel;
import main2.entities.Player;
import main2.use_case.play.PlayOutputData;
import main2.view.ViewManager;

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
