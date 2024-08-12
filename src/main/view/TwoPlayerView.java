package main.view;

import main.adapters.play.PlayController;
import main.adapters.two_player.TwoPlayerState;
import main.adapters.two_player.TwoPlayerViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TwoPlayerView extends JPanel implements PropertyChangeListener {

    private final JLabel scoreLabel;
    private final JLabel turnLabel;

    public TwoPlayerView(PlayController controller,
                         TwoPlayerViewModel viewModel) {
        PlayView playView = new PlayView(controller, viewModel);
        playView.verticallySquishTextLabel(100);
        viewModel.addPropertyChangeListener(this);

        Dimension labelSize = new Dimension(800, 50);
        // new labels for two player version
        scoreLabel = new JLabel();
        scoreLabel.setFont(viewModel.FONT);
        scoreLabel.setPreferredSize(labelSize);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel = new JLabel();
        turnLabel.setFont(viewModel.FONT);
        turnLabel.setPreferredSize(labelSize);
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add everything to the main panel
        this.setLayout(new BorderLayout());
        this.add(scoreLabel, BorderLayout.NORTH);
        this.add(turnLabel, BorderLayout.CENTER);
        this.add(playView, BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TwoPlayerState state = (TwoPlayerState) evt.getNewValue();
        this.scoreLabel.setText(state.getScore());
        this.turnLabel.setText(state.getTurn());
    }
}
