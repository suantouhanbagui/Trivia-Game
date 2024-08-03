package main.userinterface;
import main.entities.QuestionSettingOptions;
import main.entities.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class creates the main menu GUI that the player will be greeted with when first opening the game.
 * Tncludes dropdown menus for types of questions, single-player or two-player mode, and other various settings
 */
public class SettingsUI extends JFrame implements ActionListener {
    //creates private dropdown menus for each setting
    private Settings settings;
    private Settings nochangesettings;

    private final String[] categories = QuestionSettingOptions.getCategoryOptions();
    private final JComboBox<String> questionMode = new JComboBox<String>(Settings.qModes);
    private final JComboBox<String> playerMode = new JComboBox<String>(Settings.pModes);
    private final JComboBox<String> lightMode = new JComboBox<String>(Settings.lModes);
    private final JComboBox<String> cDropdown = new JComboBox<>(categories);

    //Creates the labels for the introduction and to create empty space
    private JLabel introLabel = new JLabel("Please select settings from the dropdowns:");
    private JLabel emptyLabel = new JLabel(" ");
    private JLabel emptyLabelTwo = new JLabel(" ");

    //Creates variables to store the settings the player choose
    //These are initially set to the default choice.
//    private String qMode ="Multiple Choise";
//    private String pMode ="Single Player";
//    private String lMode ="LightMode";
//    private String category = "Any Category";

    //Creates a Play button
    private JButton backButton = new JButton("Back");
    private JButton confirmButton = new JButton("Confirm");

    //Creates a font for the dropdowns
    private Font ddFont = new Font("Serif", Font.PLAIN, 40);

    public SettingsUI(Settings settings) {
        // Sets the title at the top, the size, the layout, and how to close the GUI
        this.settings = settings;
        this.nochangesettings = settings;
        setTitle("Trivia Quiz");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Creates a panel to display the introduction text at the top of the GUI
        JPanel introPanel = new JPanel(new GridLayout(0, 1));
        introLabel.setFont(new Font("Serif", Font.BOLD, 50));
        introLabel.setHorizontalAlignment(SwingConstants.CENTER);
        introPanel.add(emptyLabel);
        introPanel.add(introLabel);
        introPanel.add(emptyLabelTwo);

        //Adds the intro text panel to the GUI
        add(introPanel, BorderLayout.NORTH);

        //changes the font and adds an action listener to each dropdown
        questionMode.addActionListener(this);
        questionMode.setFont(ddFont);
        playerMode.addActionListener(this);
        playerMode.setFont(ddFont);
        lightMode.addActionListener(this);
        lightMode.setFont(ddFont);
        cDropdown.addActionListener(this);
        cDropdown.setFont(ddFont);

        //Creates a panel to display all the dropdowns
        JPanel dropdownPanel = new JPanel(new GridLayout(0, 1));
        dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.Y_AXIS));
        dropdownPanel.add(cDropdown);
        dropdownPanel.add(questionMode);
        dropdownPanel.add(playerMode);
        dropdownPanel.add(lightMode);
        cDropdown.setSelectedItem(this.settings.getCategory());
        questionMode.setSelectedItem(this.settings.getType());
        playerMode.setSelectedItem(this.settings.getGameMode());
        lightMode.setSelectedItem(this.settings.isDarkModeEnabled() ? Settings.lModes[1]:Settings.lModes[0]);

        //Adds the dropdown panel
        add(dropdownPanel, BorderLayout.CENTER);
        //Allows to playButton to do the specified action and changes the size and font of button
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(1000, 50));
        backButton.setFont(new Font("Serif", Font.BOLD, 25));

        //Adds the back button
        confirmButton.addActionListener(this);
        confirmButton.setPreferredSize(new Dimension(1000, 50));
        confirmButton.setFont(new Font("Serif", Font.BOLD, 25));

        //Adds the back button

        //allows the player to see the GUI
        JPanel submitPanel = new JPanel(new GridLayout(0, 1));
        submitPanel.add(confirmButton);
        submitPanel.add(backButton);
        add(submitPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Updates the settings variables to the settings that the user selected from the dropdowns
     * and exits the program
     * @param e An instance of the ActionEvent class to use its methods/properties
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == questionMode) {
            this.settings.setqType(Objects.requireNonNull(questionMode.getSelectedItem()).toString());
        }
        else if (e.getSource() == playerMode) {
            this.settings.setGameMode(Objects.requireNonNull(playerMode.getSelectedItem()).toString());
        }
        else if (e.getSource() == lightMode) {
            this.settings.setDarkModeEnabled(Objects.requireNonNull(lightMode.getSelectedItem()).toString().contains("Dark"));
        }
        else if (e.getSource() == cDropdown) {
            this.settings.setqCategory(Objects.requireNonNull(cDropdown.getSelectedItem()).toString());
        }
        else if (e.getSource() == backButton) {
            SwingUtilities.invokeLater(() -> new StartScreenUI(this.nochangesettings));
            dispose();
        }
        else if (e.getSource() == confirmButton) {
            SwingUtilities.invokeLater(() -> new StartScreenUI(this.settings));
            dispose();
        }
    }

    /**
     * Displays a new Main Menu UI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SettingsUI(new Settings()));
    }
}
