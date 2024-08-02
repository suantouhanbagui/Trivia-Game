package main.use_case;

import main.entities.Player;
import main.entities.Question;
import main.entities.QuestionList;

import java.util.*;

/** Implements the interface for single-player game play functions. */
public class SinglePlayerGameMode implements SinglePlayerGameModeInterface {
    private Player player;
    private final StringBuilder results = new StringBuilder();

    /**
     * Starts the game with the given player and question list.
     *
     * @param questionList The list of questions to be used in the game.
     * @param name         The name of the player.
     * @param isTest       For testing purposes only. Disables question shuffling if true.
     */
    public void startGame(QuestionList questionList, String name, Boolean isTest) {
        // Initialize player
        this.player = new Player(name);

        Scanner scanner = new Scanner(System.in);
        List<Question> questions = questionList.getQuestions();

        // Cycle through the questions once
        for (Question question : questions) {
            System.out.print("-------------------------------------------------------\n");
            System.out.println(player.getName() + ", it's your turn!");
            System.out.println(question.getQuestionText());
            List<String> options = new ArrayList<>(question.getIncorrectAnswers());
            options.add(question.getCorrectAnswer());

            // If True / False question, have True always be option 1 and False be option 2
            if (options.contains("True") && options.contains("False")) {
                Collections.sort(options, Comparator.reverseOrder());
            } else {
                // Shuffle questions if this method call is not a test.
                if (!isTest) {
                    Collections.shuffle(options);
                }
            }

            // Print options
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }

            System.out.print("Your answer (enter the number): ");
            int answerIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume the newline

            if (options.get(answerIndex).equals(question.getCorrectAnswer())) {
                player.setScore(player.getScore() + 1);
                System.out.println("Correct! Your score: " + player.getScore());
            } else {
                System.out.println("Wrong! The correct answer was: " + question.getCorrectAnswer());
            }
        }

        scanner.close();
        displayResults();
    }

    public void startGame(QuestionList questionList, String name) {
        startGame(questionList, name, false);
    }

    /** Displays the results of the game. Also updates the results. */
    private void displayResults() {
        results.append(player.toString());
        results.append("; Result: ");
        results.append(player.getName());
        results.append(" scored ");
        results.append(player.getScore());
        System.out.println("Game over! " + player.getName() + " scored " + player.getScore());
    }

    /**
     * Creates a string representation of the results of this game. This should
     * only be called after a game has been played and completed.
     *
     * @return a string representation of the results of this game.
     */
    public String getResults() {
        return results.toString();
    }
}
