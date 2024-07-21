package use_case;

import entities.Player;
import entities.Question;
import entities.QuestionList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/** Implements the interface for game play functions. */
public class GamePlayFunctions implements GamePlayFunctionsInterface {
    private Player[] players;
    private int currentPlayerIndex = 0;
    private final StringBuilder results = new StringBuilder();

    /**
     * Starts the game with the given players and question list.
     *
     * @param questionList The list of questions to be used in the game.
     * @param name1        The name of the first player.
     * @param name2        The name of the second player.
     */
    public void startGame(QuestionList questionList, String name1, String name2) {
        // Initialize players
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        this.players = new Player[]{player1, player2};

        Scanner scanner = new Scanner(System.in);
        List<Question> questions = questionList.getQuestions();

        // Cycle through the questions once
        for (Question question : questions) {
            Player currentPlayer = players[currentPlayerIndex];
            System.out.print("-------------------------------------------------------\n");
            System.out.println(currentPlayer.getName() + ", it's your turn!");
            System.out.println(question.getQuestionText());
            List<String> options = new ArrayList<>(question.getIncorrectAnswers());
            options.add(question.getCorrectAnswer());
            Collections.shuffle(options);

            // Print options
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }

            System.out.print("Your answer (enter the number): ");
            int answerIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume the newline

            if (options.get(answerIndex).equals(question.getCorrectAnswer())) {
                currentPlayer.setScore(currentPlayer.getScore() + 1);
                System.out.println("Correct! Your score: " + currentPlayer.getScore());
            } else {
                System.out.println("Wrong! The correct answer was: " + question.getCorrectAnswer());
            }

            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }

        scanner.close();
        determineWinner();
    }

    /** Determines and displays the winner of the game. Also updates the results. */
    private void determineWinner() {
        Player winner = players[0];
        results.append(winner.toString());
        results.append("; ");
        boolean tie = false;
        for (int i = 1; i < players.length; i++) {
            results.append(players[i].toString());
            results.append("; ");
            if (players[i].getScore() > winner.getScore()) {
                winner = players[i];
                tie = false;
            } else if (players[i].getScore() == winner.getScore()) {
                tie = true;
            }
        }

        if (tie) {
            results.append("Result: Tie");
            System.out.println("The game is a tie!");

        } else {
            results.append("Result: ");
            results.append(winner.getName());
            results.append(" wins!");
            System.out.println("The winner is: " + winner.getName() + " with a score of " + winner.getScore());
        }

        }

    /**
     * Creates a string representation of the results of this game. This should
     * only be called after a game has been played and completed.
     *
     * @return a string representation of the results of this game.
     */
    public String getResults(){
        return results.toString();
    }
}
