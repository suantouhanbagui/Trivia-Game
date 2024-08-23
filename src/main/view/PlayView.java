package main.view;

import main.adapters.play.PlayController;
import main.adapters.play.PlayState;
import main.adapters.play.PlayViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Screen for users to play the quiz. Has a label showing quiz progress, a
 * label with the question text and buttons for selecting an answer.
 */
public class PlayView extends JPanel implements PropertyChangeListener, ActionListener {
    /**
     * Whenever a button is pressed to answer a question, invoke this
     * controller to pass the answer down to the use case interactor.
     */
    private final PlayController controller;

    /** Shows how many questions have been answered and the total number. */
    private final JLabel progressLabel;
    /** Displays the question text. */
    private final JLabel qTextLabel;

    /** Array of buttons for choosing an answer. */
    private final JButton[] buttons;
    /** Internal panel where the buttons are displayed. */
    private final JPanel buttonPanel;

    /**
     * Instantiate a new {@code PlayView}.
     *
     * @param controller to invoke when the user answers a question by pressing
     *        a button.
     * @param viewModel stores the state for this view.
     */
    public PlayView(PlayController controller,
                    PlayViewModel viewModel) {
        this.controller = controller;
        viewModel.addPropertyChangeListener(this);

        // labels to show question number and text
        progressLabel = new JLabel();
        progressLabel.setFont(viewModel.FONT);
        progressLabel.setPreferredSize(new Dimension(800, 50));
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qTextLabel = new JLabel();
        qTextLabel.setFont(viewModel.FONT);
        qTextLabel.setPreferredSize(new Dimension(800, 250));
        qTextLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // panel for buttons
        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setPreferredSize(new Dimension(800, 200));
        buttons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(viewModel.FONT);
            buttons[i].setPreferredSize(new Dimension(800, 100));
            buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
            buttons[i].addActionListener(this);
        }

        // add everything to the main panel
        this.setLayout(new BorderLayout());
        this.add(progressLabel, BorderLayout.NORTH);
        this.add(qTextLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * The preferred size of the label where question text is displayed is 800
     * by 250. This sets the size of the label to {@code 250 - height}, leaving
     * extra space tp be filled by additional labels.
     *
     * @param height reduce height of the question text label by this amount.
     */
    public void verticallySquishTextLabel(int height) {
        qTextLabel.setPreferredSize(new Dimension(800, 250 - height));
    }

    /**
     * Update the progress label, question text and the buttons with the new
     * options.
     *
     * @param state holds the new values.
     */
    private void setFields(PlayState state) {
        this.progressLabel.setText(state.getProgress());
        this.qTextLabel.setText(state.getText());
        String[] options = state.getOptions();
        buttonPanel.removeAll();
        for (int i = 0; i < 4; i++) {
            if (options[i] != null) {
                buttons[i].setText(options[i]);
                buttons[i].updateUI();
                buttonPanel.add(buttons[i]);
            }
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Invokes the controller and passes in the answer whenever the user
     * presses a button to select an option.
     *
     * @param e the {@code ActionEvent}. The source must be castable to
     *        {@code JButton}.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        controller.execute(source.getText());
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
        PlayState state = (PlayState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        } else {
            if (state.getFeedback() != null) {
                JOptionPane.showMessageDialog(this, state.getFeedback());
            }
            setFields(state);
        }
    }
}
