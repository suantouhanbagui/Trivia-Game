package main.userinterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class creates the main menu GUI that the player will be greeted with when first opening the game.
 * Tncludes dropdown menus for types of questions, single-player or two-player mode, and other various settings
 */
public class MainMenuUI extends JFrame implements ActionListener {
    //creates private dropdown menus for each setting
    private final String[] qModes = {"Multiple Choise", "True and False", "Mixed"};
    private final String[] pModes = {"Single Player", "Two Player"};
    private final String[] lModes = {"Light Mode", "Dark Mode"};
    private final JComboBox<String> questionMode = new JComboBox<String>(qModes);
    private final JComboBox<String> playerMode = new JComboBox<String>(pModes);
    private final JComboBox<String> lightMode = new JComboBox<String>(lModes);

    //Creates the labels for the introduction
    private JLabel introLabel = new JLabel("Please select settings from the dropdowns:");

    //Creates variables to store the settings the player choose
    //These are initially set to the default choice.
    private String qMode ="Multiple Choise";
    private String pMode ="Single Player";
    private String lMode ="LightMode";

    //Creates a Play button
    private JButton backButton = new JButton("Back");


    public MainMenuUI() {
        // Sets the title at the top, the size, the layout, and how to close the GUI
        setTitle("Trivia Quiz");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Creates a panel to display the introduction text at the top of the GUI
        JPanel introPanel = new JPanel(new GridLayout(0, 1));
        introLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        introLabel.setHorizontalAlignment(SwingConstants.CENTER);
        introPanel.add(introLabel);

        //Adds the intro text panel to the GUI
        add(introPanel, BorderLayout.NORTH);

        //Creates a panel to display all the dropdowns
        JPanel dropdownPanel = new JPanel(new GridLayout(0, 1));
        dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.Y_AXIS));
        questionMode.addActionListener(this);
        dropdownPanel.add(questionMode);
        dropdownPanel.add(playerMode);
        dropdownPanel.add(lightMode);

        //Adds the dropdown panel
        add(dropdownPanel, BorderLayout.CENTER);

        //Allows to playButton to do the specified action and adds the playButton
        backButton.addActionListener(this);
        add(backButton, BorderLayout.SOUTH);

        //allows the player to see the GUI
        setVisible(true);

    }

    /**
     * Updates the settings variables to the settings that the user selected from the dropdowns
     * and exits the program
     * @param e An instance of the ActionEvent class to use its methods/properties
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == questionMode) {
            this.qMode = Objects.requireNonNull(questionMode.getSelectedItem()).toString();
        }
        else if (e.getSource() == playerMode) {
            this.pMode = Objects.requireNonNull(playerMode.getSelectedItem()).toString();
        }
        else if (e.getSource() == lightMode) {
            this.lMode = Objects.requireNonNull(lightMode.getSelectedItem()).toString();
        }
        else if (e.getSource() == backButton) {
            SwingUtilities.invokeLater(StartScreenUI::new);
            dispose();
        }
    }

    /**
     * Displays a new Main Menu UI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenuUI::new);
    }
}
