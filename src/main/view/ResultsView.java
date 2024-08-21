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

/** Window for users to view results of past games. */
public class ResultsView extends JPanel implements PropertyChangeListener, ActionListener {
    /** Invoked when a button is pressed. */
    private final ResultsController controller;
    /** Holds the state of this view. */
    private final ResultsViewModel viewModel;

    /** Text area for displaying the results. */
    private final JTextArea resultsArea;
    /** Button to return to start screen. */
    private final JButton backButton;

    /**
     * Instantiate a new {@code ResultsViewObject}.
     *
     * @param controller to invoke when the back button is pressed.
     * @param viewModel stores the state for this view.
     */
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

    /**
     * Invokes the controller, passing in necessary data.
     *
     * @param e the {@code ActionEvent}.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.execute();
    }

    /**
     * Must be called whenever the state of {@code viewModel} is changed. Displays an
     * error message if the state has one.
     *
     * @param evt A {@code PropertyChangeEvent} object describing the event source
     *        and the property that has changed.
     */
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
