package main.userinterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import main.data_access.TriviaDB;
import main.entities.Player;
import main.entities.QuestionList;
import main.entities.Question;
import main.entities.Settings;

public class QuizUI extends JFrame {
    private final JLabel questionLabel;
    private final List<JButton> answerButtons;
    private QuestionList questionList;
    private Question currentQuestion;

    //created a Font for the buttons
    private Font buttonFont = new Font("Serif", Font.PLAIN, 25);

    //created empty Labels for empty space in the UI
    private JLabel eLabel = new JLabel(" ");
    private JLabel eLabelTwo = new JLabel(" ");
    private Settings settings;
    private int playernumber;
    private int currentplayer;
    private List<Player> players;
//    private JLabel statusLabel;
    private JLabel leftStatusLabel;
    private JLabel rightStatusLabel;

    public QuizUI(Settings settings) {
        this.settings = settings;
        this.playernumber = this.settings.getGameMode().contains("Two") ? 2:1;
        this.currentplayer = -1;
        players = new ArrayList<>();
        for (int i = 0; i < playernumber; i++) {
            players.add(new Player("Player" + (i+1)));
        }
        setTitle("Trivia Quiz");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel(new GridLayout(0, 1));
        questionLabel = new JLabel("Question will be here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Serif", Font.BOLD, 20));
        questionPanel.add(eLabel);
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        questionPanel.add(eLabelTwo);

        //add the question panel
        add(questionPanel, BorderLayout.NORTH);

        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new GridLayout(2, 2));
        answerButtons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton("Answer " + (i + 1));
            button.addActionListener(new AnswerButtonListener());
            button.setFont(buttonFont);
            answerButtons.add(button);
            answerPanel.add(button);
        }
        add(answerPanel, BorderLayout.CENTER);
        JPanel statusPanel = new JPanel(new BorderLayout());
        leftStatusLabel = new JLabel(getPlayerStatus(0), SwingConstants.LEFT);
        rightStatusLabel = new JLabel(getPlayerStatus(1), SwingConstants.RIGHT);

        statusPanel.add(leftStatusLabel, BorderLayout.WEST);
        statusPanel.add(rightStatusLabel, BorderLayout.EAST);

        add(statusPanel, BorderLayout.SOUTH);

        loadQuestions();
        loadNextQuestion();

        setVisible(true);
    }

    private void loadNextQuestion() {
        if (questionList.hasMoreQuestions()) {
            currentplayer = (currentplayer + 1) % playernumber;
            currentQuestion = questionList.getNextQuestion();
            questionLabel.setText(currentQuestion.getQuestionText());

            List<String> answers = new ArrayList<>(currentQuestion.getIncorrectAnswers());
            answers.add(currentQuestion.getCorrectAnswer());
            Collections.shuffle(answers);

            int answerCount = answers.size();
            for (int i = 0; i < 4; i++) {
                if (i < answerCount) {
                    answerButtons.get(i).setText(answers.get(i));
                    answerButtons.get(i).setVisible(true);
                } else {
                    answerButtons.get(i).setVisible(false);
                }
            }
        } else {
            SwingUtilities.invokeLater(() -> new ResultsUI(settings));
//            SwingUtilities.invokeLater(() -> new ResultsUI(settings,players));
            // if I will use the results UI after the game finished
            this.dispose();
        }
    }

    private void loadQuestions() {
        questionList = new TriviaDB().getQuestions(this.settings.getAmount() * this.playernumber, this.settings.getCategory(), "Easy", "Multiple Choice");
    }

    private String getPlayerStatus(int playerIndex) {
        if (playerIndex < players.size()) {
            Player player = players.get(playerIndex);
            return player.getName() + ": " + player.getScore();
        }
        return "";
    }

    private class AnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String selectedAnswer = source.getText();
            if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
                JOptionPane.showMessageDialog(QuizUI.this, "Correct!");
                players.get(currentplayer).addScore();
            } else {
                JOptionPane.showMessageDialog(QuizUI.this, "Wrong!");
            }
            leftStatusLabel.setText(getPlayerStatus(0));
            rightStatusLabel.setText(getPlayerStatus(1));
            loadNextQuestion();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizUI(new Settings()));
    }
}
