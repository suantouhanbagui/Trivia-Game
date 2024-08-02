package main.use_case;

/**
 * This interface defines the common methods required for game play functionality.
 * Implementations of this interface handle game results.
 */
public interface GameModeInterface {
    /**
     * Returns the results of the game as a string.
     *
     * @return a string representation of the results of the game.
     */
    String getResults();
}
