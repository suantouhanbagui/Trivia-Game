package app;

import entities.QuestionList;
import data_access.TriviaDB;
import data_access.TriviaDBInterface;
import use_case.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to our trivia game ~~~~");
        // Gather settings for the game
        EditGameSettings settings = new EditGameSettings();

        // Create questions
        TriviaDBInterface triviaDB = new TriviaDB();
        QuestionList questionList = triviaDB.getQuestions(settings.getAmount(), settings.getCategory(),
                settings.getDifficulty(), settings.getType());

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
