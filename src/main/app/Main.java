package main.app;

import main.entities.QuestionList;
import main.data_access.TriviaDB;
import main.data_access.TriviaDBInterface;
import main.use_case.*;
import main.userinterface.StartScreenUI;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Welcome!");
//        // Gather settings for the game
//        EditGameSettings settings = new EditGameSettings();
//
//        // Create questions
//        TriviaDBInterface triviaDB = new TriviaDB();
//        QuestionList questionList = triviaDB.getQuestions(settings.getAmount(), settings.getCategory(),
//                settings.getDifficulty(), settings.getType());
//
//        // Initialize the game (Two Player)
////        TwoPlayerGameModeInterface game = new TwoPlayerGameMode();
////        game.startGame(questionList, "Alice", "Bob");
//
//        // Initialize the game (Single Player)
//        SinglePlayerGameModeInterface game = new SinglePlayerGameMode();
//        game.startGame(questionList, "Alice");
//
//        // After the game, record the results
//        RecordResult record = new RecordResult();
//        record.record(game);
//
//        // Look at past results
//        // Exception handled by the UI currently, this is just for demo purposes
//        try {
//            RetrieveResults retrieve = new RetrieveResults();
//            System.out.println(retrieve.retrieve());
//        }
//        catch (Exception e){
//            e.printStackTrace(System.out);
//        }
        StartScreenUI.main(args);
    }
}
