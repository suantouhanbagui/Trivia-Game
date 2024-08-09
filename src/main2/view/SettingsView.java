package main2.view;

import main2.adapters.settings.SettingsController;
import main2.adapters.settings.SettingsState;
import main2.adapters.settings.SettingsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SettingsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SettingsController controller;
    private final SettingsViewModel viewModel;

    private final JTextField amountField;
    private final JComboBox<String> categoryDropdown;
    private final JComboBox<String> difficultyDropdown;
    private final JComboBox<String> typeDropdown;
    private final JComboBox<String> gamemodeDropdown;
    private final JComboBox<String > darkModeDropdown;

    private final JButton confirmButton;

    public SettingsView(SettingsController controller,
                        SettingsViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        // create panel for instructions
        JLabel instructionsLabel = new JLabel(viewModel.INSTRUCTIONS_LABEL);
        instructionsLabel.setFont(viewModel.FONT);
        instructionsLabel.setPreferredSize(new Dimension(800, 200));

        // create panel for dropdowns
        JPanel dropdownsPanel = new JPanel();
        dropdownsPanel.setLayout(new GridLayout(6, 2));
        dropdownsPanel.setPreferredSize(new Dimension(800, 250));
        // amount
        JLabel amountLabel = new JLabel(viewModel.AMOUNT_LABEL);
        amountLabel.setFont(viewModel.FONT);
        dropdownsPanel.add(amountLabel);
        amountField = new JTextField();
        amountField.setFont(viewModel.FONT);
        dropdownsPanel.add(amountField);
        // category
        JLabel categoryLabel = new JLabel(viewModel.CATEGORY_LABEL);
        categoryLabel.setFont(viewModel.FONT);
        dropdownsPanel.add(categoryLabel);
        categoryDropdown = new JComboBox<>(viewModel.CATEGORY_OPTIONS);
        categoryDropdown.setFont(viewModel.FONT);
        dropdownsPanel.add(categoryDropdown);
        // difficulty
        JLabel difficultyLabel = new JLabel(viewModel.DIFFICULTY_LABEL);
        difficultyLabel.setFont(viewModel.FONT);
        dropdownsPanel.add(difficultyLabel);
        difficultyDropdown = new JComboBox<>(viewModel.DIFFICULTY_OPTIONS);
        difficultyDropdown.setFont(viewModel.FONT);
        dropdownsPanel.add(difficultyDropdown);
        // type
        JLabel typeLabel = new JLabel(viewModel.TYPE_LABEL);
        typeLabel.setFont(viewModel.FONT);
        dropdownsPanel.add(typeLabel);
        typeDropdown = new JComboBox<>(viewModel.TYPE_OPTIONS);
        typeDropdown.setFont(viewModel.FONT);
        dropdownsPanel.add(typeDropdown);
        // gamemode
        JLabel gamemodeLabel = new JLabel(viewModel.GAMEMODE_LABEL);
        gamemodeLabel.setFont(viewModel.FONT);
        dropdownsPanel.add(gamemodeLabel);
        gamemodeDropdown = new JComboBox<>(viewModel.GAMEMODE_OPTIONS);
        gamemodeDropdown.setFont(viewModel.FONT);
        dropdownsPanel.add(gamemodeDropdown);
        // light/dark mode
        JLabel darkModeLabel = new JLabel(viewModel.TOGGLE_DARK_MODE_LABEL);
        darkModeLabel.setFont(viewModel.FONT);
        dropdownsPanel.add(darkModeLabel);
        darkModeDropdown = new JComboBox<>(viewModel.LIGHT_DARK_MODE_OPTIONS);
        darkModeDropdown.setFont(viewModel.FONT);
        dropdownsPanel.add(darkModeDropdown);

        // create confirm button
        confirmButton = new JButton(viewModel.CONFIRM_BUTTON_LABEL);
        confirmButton.setFont(viewModel.FONT);
        confirmButton.setPreferredSize(new Dimension(800, 50));
        confirmButton.addActionListener(this);

        // add everything to the main panel
        this.setLayout(new BorderLayout());
        this.add(instructionsLabel, BorderLayout.NORTH);
        this.add(dropdownsPanel, BorderLayout.CENTER);
        this.add(confirmButton, BorderLayout.SOUTH);

        this.setFields(viewModel.getState());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.execute(amountField.getText(),
                (String) categoryDropdown.getSelectedItem(),
                (String) difficultyDropdown.getSelectedItem(),
                (String) typeDropdown.getSelectedItem(),
                (String) gamemodeDropdown.getSelectedItem(),
                (String) darkModeDropdown.getSelectedItem());
    }

    private void setFields(SettingsState state) {
        amountField.setText(state.getAmount());
        categoryDropdown.setSelectedItem(state.getCategory());
        difficultyDropdown.setSelectedItem(state.getDifficulty());
        typeDropdown.setSelectedItem(state.getType());
        gamemodeDropdown.setSelectedItem(state.getGamemode());
        darkModeDropdown.setSelectedItem((state.getDarkMode()));

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsState state = (SettingsState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
