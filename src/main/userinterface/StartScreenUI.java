package main.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartScreenUI extends JFrame {
    private JLabel titleLabel = new JLabel("Trivia Game");

    private JButton startButton = new JButton("Start");
    private JButton settingsButton = new JButton("Settings");
    private JButton exitButton = new JButton("Exit");

    public StartScreenUI() {
        setTitle("Trivia Quiz");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);

        add(titlePanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        startButton.setPreferredSize(new Dimension(400, 50));
        optionsPanel.add(startButton);
        settingsButton.setPreferredSize(new Dimension(400, 50));
        optionsPanel.add(settingsButton);
        exitButton.setPreferredSize(new Dimension(400, 50));
        optionsPanel.add(exitButton);

        add(optionsPanel, BorderLayout.SOUTH);

        setVisible(true);

    }


    /**
     * Displays a new Start Screen UI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartScreenUI::new);
    }
}
