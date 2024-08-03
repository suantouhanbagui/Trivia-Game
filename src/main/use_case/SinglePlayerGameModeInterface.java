package main.use_case;

import main.entities.QuestionList;

/**
 * This interface defines the methods required for single-player game play functionality.
 * Implementations of this interface handle starting and managing the single-player game process.
 */
public interface SinglePlayerGameModeInterface extends GameModeInterface {
    /**
     * Starts the game with the given player and question list.
     *
     * @param questionList The list of questions to be used in the game.
     * @param name         The name of the player.
     */
    void startGame(QuestionList questionList, String name);

    String getResults();
}
