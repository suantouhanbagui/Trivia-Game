package main.app;

import main.entities.QuestionList;
import main.data_access.TriviaDB;
import main.data_access.TriviaDBInterface;
import main.use_case.GamePlayFunctionsInterface;
import main.use_case.GamePlayFunctions;
import main.use_case.RecordResult;
import main.use_case.RetrieveResults;

public class Main {
    public static void main(String[] args) {
        // Create questions
        TriviaDBInterface triviaDB = new TriviaDB();
        QuestionList questionList = triviaDB.getQuestions(10, "General Knowledge", "Easy", "Multiple Choice");

        // Initialize the game
        GamePlayFunctionsInterface game = new GamePlayFunctions();
        game.startGame(questionList, "Alice", "Bob");

        // After the game, record the results
        RecordResult record = new RecordResult();
        record.record(game);

        // Look at past results
        RetrieveResults retrieve = new RetrieveResults();
        System.out.println(retrieve.retrieve());
    }
}
