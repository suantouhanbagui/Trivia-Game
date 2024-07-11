package app;

import entities.QuestionList;
import data_access.TriviaDB;
import data_access.TriviaDBInterface;
import use_case.GamePlayFunctionsInterface;
import use_case.GamePlayFunctions;

public class Main {
    public static void main(String[] args) {
        // Create questions
        TriviaDBInterface triviaDB = new TriviaDB();
        QuestionList questionList = triviaDB.getQuestions(10, "General Knowledge", "Easy", "Multiple Choice");

        // Initialize the game
        GamePlayFunctionsInterface game = new GamePlayFunctions();
        game.startGame(questionList, "Alice", "Bob");
    }
}
