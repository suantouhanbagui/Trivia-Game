package main.view;

import main.adapters.results.ResultsController;
import main.adapters.results.ResultsState;
import main.adapters.results.ResultsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResultsView extends JPanel implements PropertyChangeListener, ActionListener {
    private final ResultsController controller;
    private final ResultsViewModel viewModel;

    private final JTextArea resultsArea;
    private final JButton backButton;

    public ResultsView(ResultsController controller,
                       ResultsViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        // create title label
        JLabel titleLabel = new JLabel(viewModel.TITLE_LABEL);
        titleLabel.setFont(viewModel.FONT);
        titleLabel.setPreferredSize(new Dimension(800, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // create label to store results
        resultsArea = new JTextArea(viewModel.getState().getResults());
        resultsArea.setEditable(false);
        resultsArea.setFont(viewModel.FONT);
        resultsArea.setPreferredSize(new Dimension(800, 400));

        // create back button
        backButton = new JButton(viewModel.BACK_BUTTON_LABEL);
        backButton.setFont(viewModel.FONT);
        backButton.setPreferredSize(new Dimension(800, 50));
        backButton.addActionListener(this);

        // add everything to main panel
        this.setLayout(new BorderLayout());
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(resultsArea, BorderLayout.CENTER);
        this.add(backButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.execute();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ResultsState state = (ResultsState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        } else {
            resultsArea.setText(state.getResults());
        }
    }
}
