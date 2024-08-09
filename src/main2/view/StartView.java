package main2.view;

import main2.adapters.play.PlayController;
import main2.adapters.results.ResultsController;
import main2.adapters.settings.SettingsController;
import main2.adapters.start.StartState;
import main2.adapters.start.StartViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SettingsController settingsController;
    private final ResultsController resultsController;
    private final PlayController playController;
    private final StartViewModel viewModel;

    private final JButton startButton;
    private final JButton settingsButton;
    private final JButton resultsButton;
    private final JButton exitButton;

    public StartView(SettingsController settingsController,
                     ResultsController resultsController,
                     PlayController playController,
                     StartViewModel viewModel) {
        this.settingsController = settingsController;
        this.resultsController = resultsController;
        this.playController = playController;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        // create panel for title
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(viewModel.TITLE_LABEL);
        titleLabel.setFont(viewModel.TITLE_FONT);
        titleLabel.setPreferredSize(new Dimension(800, 300));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);

        // create panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        Dimension buttonDimension = new Dimension(800, 50);
        startButton = new JButton(viewModel.START_BUTTON_LABEL);
        startButton.setFont(viewModel.BUTTON_FONT);
        startButton.setName("start");
        startButton.setPreferredSize(buttonDimension);
        startButton.addActionListener(this);
        buttonPanel.add(startButton);
        settingsButton = new JButton(viewModel.SETTINGS_BUTTON_LABEL);
        settingsButton.setFont(viewModel.BUTTON_FONT);
        settingsButton.setName("settings");
        settingsButton.setPreferredSize(buttonDimension);
        settingsButton.addActionListener(this);
        buttonPanel.add(settingsButton);
        resultsButton = new JButton(viewModel.RESULTS_BUTTON_LABEL);
        resultsButton.setFont(viewModel.BUTTON_FONT);
        resultsButton.setName("results");
        resultsButton.setPreferredSize(buttonDimension);
        resultsButton.addActionListener(this);
        buttonPanel.add(resultsButton);
        exitButton = new JButton(viewModel.EXIT_BUTTON_LABEL);
        exitButton.setFont(viewModel.BUTTON_FONT);
        exitButton.setPreferredSize(buttonDimension);
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        // add items to the main panel
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == startButton) {
            playController.prepareView();
        } else if (button == settingsButton) {
            settingsController.prepareView();
        } else if (button == resultsButton) {
            resultsController.prepareView();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StartState state = (StartState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
