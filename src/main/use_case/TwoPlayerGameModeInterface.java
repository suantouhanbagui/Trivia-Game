package main.use_case;

import main.entities.QuestionList;

/**
 * This interface defines the methods required for game play functionality.
 * Implementations of this interface handle starting and managing the game process.
 */
public interface TwoPlayerGameModeInterface extends GameModeInterface {
    /**
     * Starts the game with the given players and question list.
     *
     * @param questionList The list of questions to be used in the game.
     * @param name1        The name of the first player.
     * @param name2        The name of the second player.
     */
    void startGame(QuestionList questionList, String name1, String name2);

    String getResults();
}
