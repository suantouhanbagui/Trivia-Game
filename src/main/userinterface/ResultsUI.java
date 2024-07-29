package main.userinterface;

import com.formdev.flatlaf.FlatDarculaLaf;
import main.use_case.RetrieveResults;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class ResultsUI extends JFrame implements ActionListener {

    /**
     * Initialise a new ResultsUI and makes it visible to the user.
     */
    public ResultsUI(){
        // just to make it dark mode for demo purposes
        // later I think there should be a button(? or sth) on the main menu or something to toggle dark/light
        FlatDarculaLaf.setup();
        FlatDarculaLaf.updateUI();

        // formatting the frame
        setLayout(new BorderLayout());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trivia Quiz");

        // creating text panel for displaying the results
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // retrieving the results to be printed
        try {
        RetrieveResults resultRetriever = new RetrieveResults();
        ArrayList<String> results = resultRetriever.retrieve();
        JLabel title = new JLabel("Previous Game Results");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);
        for (String result : results) {
            textArea.append(result + '\n');
        }

        // handle exception
        } catch (IOException ioException){
            textArea.append("UH OH! IOException occurred...\n");
            textArea.append("Sorry, could not get results :(\n");
        }

        // add the text area
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(new JScrollPane(textArea));
        add(panel, BorderLayout.CENTER);

        // creating back button to exit from results screen
        JPanel back = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        back.add(backButton);
        add(back, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Implementation of actionPerformed method. Disposes of the frame once the back button is clicked.
     * @param e The action event performed, which is the back button being clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

    /**
     * Displays a new Results UI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResultsUI::new);
    }
}
