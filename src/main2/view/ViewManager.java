package main2.view;

import main2.adapters.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewManager {
    private CardLayout cardLayout = new CardLayout();
    private JPanel views = new JPanel(cardLayout);
    private ArrayList<String> viewNames = new ArrayList<>();
    private JFrame frame;

    public ViewManager(JFrame frame) {
        this.frame = frame;
        frame.add(views);
    }

    public void addView(JPanel view, ViewModel viewModel) {
        views.add(view, viewModel.getViewName());
        viewNames.add(viewModel.getViewName());
    }

    public void setActiveView(String viewName) {
        cardLayout.show(views, viewName);
        frame.pack();
    }
}
