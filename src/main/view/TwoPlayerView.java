package main.view;

import main.adapters.play.PlayController;
import main.adapters.two_player.TwoPlayerState;
import main.adapters.two_player.TwoPlayerViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Screen for users to play the quiz in two player mode. This screen has the
 * same appearance as the PlayView screen, but also include a scoreboard and
 * label to tell the players whose turn it is.
 */
public class TwoPlayerView extends JPanel implements PropertyChangeListener {
    /** Shows the two players' scores. */
    private final JLabel scoreLabel;
    /** Shows which player's turn it is. */
    private final JLabel turnLabel;

    /**
     * Instantiate a new {@code TwoPlayerView}.
     *
     * @param controller to invoke when the user answers a question by pressing
     *        a button.
     * @param viewModel stores the state for this view.
     */
    public TwoPlayerView(PlayController controller,
                         TwoPlayerViewModel viewModel) {
        // use PlayView to show quiz progress, question text and deal with option selection
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

    /**
     * The view model uses {@code PropertyChangeSupport} from beans to invoke
     * this and alert the view to change without violating DIP. If the new
     * state has an error message, then display it. Otherwise, show the
     * feedback message if there is one and display the next question.
     *
     * @param evt A PropertyChangeEvent object describing the event source and
     *        the property that has changed. {@code evt.getNewValue()} must be
     *        castable to {@code PlayState}.
     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TwoPlayerState state = (TwoPlayerState) evt.getNewValue();
        this.scoreLabel.setText(state.getScore());
        this.turnLabel.setText(state.getTurn());
    }
}
