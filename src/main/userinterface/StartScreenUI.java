package main.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreenUI extends JFrame implements ActionListener{
    //creates a label for the title of the Game
    private JLabel titleLabel = new JLabel("Trivia Quiz");

    //creates a start, settings, result, and exit button that are labeled respectively
    private JButton startButton = new JButton("Start");
    private JButton settingsButton = new JButton("Settings");
    private JButton resultsButton = new JButton("Previous Results");
    private JButton exitButton = new JButton("Exit");

    //create a variable that holds the font for all the buttons
    private Font buttonFont = new Font("Serif", Font.PLAIN, 40);

    public StartScreenUI() {
        // Sets the title at the top, the size, the layout, and how to close the GUI
        setTitle("Trivia Quiz");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //creates a panel to store the title at the top of the UI and changes the text font
        JPanel titlePanel = new JPanel();
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 150));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);

        //adds the title panel
        add(titlePanel, BorderLayout.NORTH);

        //changes the font and adds an action listener to each button
        startButton.setPreferredSize(new Dimension(1000, 75));
        startButton.setFont(buttonFont);
        startButton.addActionListener(this);
        settingsButton.setPreferredSize(new Dimension(1000, 75));
        settingsButton.setFont(buttonFont);
        settingsButton.addActionListener(this);
        resultsButton.setPreferredSize(new Dimension(1000, 75));
        resultsButton.setFont(buttonFont);
        resultsButton.addActionListener(this);
        exitButton.setPreferredSize(new Dimension(1000, 75));
        exitButton.setFont(buttonFont);
        exitButton.addActionListener(this);

        //creates a panel to store all the buttons
        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        optionsPanel.add(startButton);
        optionsPanel.add(settingsButton);
        optionsPanel.add(resultsButton);
        optionsPanel.add(exitButton);

        //adds the options panel
        add(optionsPanel, BorderLayout.SOUTH);

        //allows the player to see the GUI
        setVisible(true);
    }
    /**
     * Preforms the desired action depending on what button is pressed
     * if the start button is pressed, the game starts
     * if the settings button is pressed, the settings screen appears and the start menu closes
     * if the exit button is pressed, the UI closes and program stops
     * @param e An instance of the ActionEvent class to use its methods/properties
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            SwingUtilities.invokeLater(QuizUI::new);
            dispose();
        }
        else if (e.getSource() == settingsButton) {
            SwingUtilities.invokeLater(MainMenuUI::new);
            dispose();
        }
        else if (e.getSource() == resultsButton) {
            SwingUtilities.invokeLater(ResultsUI::new);
            dispose();
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    /**
     * Displays a new Start Screen UI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartScreenUI::new);
    }
}
