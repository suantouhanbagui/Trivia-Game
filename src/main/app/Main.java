package main.app;

import main.entities.QuestionList;
import main.data_access.TriviaDB;
import main.data_access.TriviaDBInterface;
import main.use_case.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome!");
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
        // Exception handled by the UI currently, this is just for demo purposes
        try {
            RetrieveResults retrieve = new RetrieveResults();
            System.out.println(retrieve.retrieve());
        }
        catch (Exception e){
            e.printStackTrace(System.out);
        }
    }
}
