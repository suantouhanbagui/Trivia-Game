package main2.view;

import main2.adapters.play.PlayController;
import main2.adapters.play.PlayState;
import main2.adapters.play.PlayViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayView extends JPanel implements PropertyChangeListener, ActionListener {
    private final PlayController controller;

    private final JLabel progressLabel;
    private final JLabel qTextLabel;

    private final JButton[] buttons;
    private final JPanel buttonPanel;

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
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        buttons = new JButton[] {button1, button2, button3, button4};
        for (JButton button:buttons) {
            button.setFont(viewModel.FONT);
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(800, 100));
            button.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // add everything to the main panel
        this.setLayout(new BorderLayout());
        this.add(progressLabel, BorderLayout.NORTH);
        this.add(qTextLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void verticallySquishTextLabel(int height) {
        qTextLabel.setPreferredSize(new Dimension(800, 250 - height));
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        controller.execute(source.getText());
    }

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
