package main.view;

import main.adapters.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** Allows switching between different views. */
public class ViewManager {
    /** Used to control which view is shown. */
    private final CardLayout cardLayout = new CardLayout();
    /** Panel that stores the views. */
    private final JPanel views = new JPanel(cardLayout);
    /** Frame where the views are displayed. */
    private final JFrame frame;

    /**
     * Instantiate a new {@code ViewManager}.
     *
     * @param frame
     */
    public ViewManager(JFrame frame) {
        this.frame = frame;
        frame.add(views);
    }

    /**
     * Add a new view so that it can be shown later.
     *
     * @param view to be added to the card layout.
     * @param viewModel for the view.
     */
    public void addView(JPanel view, ViewModel viewModel) {
        views.add(view, viewModel.getViewName());
    }

    /**
     * Set a view as active.
     *
     * @param viewName name of its corresponding view model.
     */
    public void setActiveView(String viewName) {
        cardLayout.show(views, viewName);
        frame.pack();
    }
}
