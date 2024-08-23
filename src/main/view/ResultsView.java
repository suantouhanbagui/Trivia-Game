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

/**
 * Screen for users to view results of their past games. The results are
 * displayed in a JTextArea.
 */
public class ResultsView extends JPanel implements PropertyChangeListener, ActionListener {
    /** Invoked when a button is pressed. */
    private final ResultsController controller;
    /** Holds the state of this view. */
    private final ResultsViewModel viewModel;

    /** Text area where results are displayed. */
    private final JTextArea resultsArea;
    /** Button to return to the start screen. */
    private final JButton backButton;

    /**
     * Instantiate a new {@code ResultsView}.
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
     * Invokes the controller when a button is pressed, passing in necessary
     * data. Currently, this view only has a back button.
     *
     * @param e the {@code ActionEvent}. The source must be castable to
     *        {@code JButton}.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.execute();
    }

    /**
     * The view model uses {@code PropertyChangeSupport} from beans to invoke
     * this and alert the view to change without violating DIP. This either
     * updates the results in the JTextArea or displays an error message if the
     * state has one, but does not do both.
     *
     * @param evt A {@code PropertyChangeEvent} object describing the event
     *        source and the property that has changed.
     *        {@code evt.getNewValue()} must be castable to
     *        {@code ResultsState}.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ResultsState state = (ResultsState) evt.getNewValue();
        if (state.getError() != null) {
            // show error message if there is one
            JOptionPane.showMessageDialog(this, state.getError());
        } else {
            // otherwise, update the results
            resultsArea.setText(state.getResults());
        }
    }
}
