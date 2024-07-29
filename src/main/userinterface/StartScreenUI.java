package main.userinterface;

import javax.swing.*;

public class StartScreenUI extends JFrame {


    public StartScreenUI() {

    }


    /**
     * Displays a new Start Screen UI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenuUI::new);
    }
}
