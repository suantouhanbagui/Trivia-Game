package entities;

import main.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Player} entity.
 * This class tests the functionality of the Player class methods to ensure correct behavior.
 */
class PlayerTest {

    private Player player;

    /**
     * Sets up a new Player instance before each test.
     * This ensures each test runs with a fresh Player object.
     */
    @BeforeEach
    void setUp() {
        player = new Player("Alice");
    }

    /**
     * Tests the {@link Player#getName()} method.
     * Verifies that the name of the player is correctly set during instantiation.
     */
    @Test
    void testGetName() {
        assertEquals("Alice", player.getName(), "The player's name should be 'Alice'.");
    }

    /**
     * Tests the {@link Player#getScore()} method.
     * Ensures that the player's score is initialized to 0 upon creation.
     */
    @Test
    void testInitialScore() {
        assertEquals(0, player.getScore(), "The initial score should be 0.");
    }

    /**
     * Tests the {@link Player#stepScore()} method.
     * Checks that the player's score is incremented by 1 when the stepScore method is called.
     */
    @Test
    void testStepScore() {
        player.stepScore();
        assertEquals(1, player.getScore(), "The score should be incremented to 1 after one step.");
    }

    /**
     * Tests the {@link Player#toString()} method.
     * Confirms that the string representation of the player matches the expected format.
     * The initial format should include the player's name and an initial score of 0.
     */
    @Test
    void testToString() {
        assertEquals("Alice: 0", player.toString(), "The string representation should be 'Alice: 0' initially.");
        player.stepScore();
        assertEquals("Alice: 1", player.toString(), "The string representation should be 'Alice: 1' after one step.");
    }

    /**
     * Tests the {@link Player#compareTo(Player)} method.
     * Validates that the comparison between players is accurate based on their scores.
     * The test checks if:
     * 1. A player with a lower score is correctly compared as less than another player.
     * 2. Players with the same score are considered equal.
     * 3. A player with a higher score is correctly compared as greater than another player.
     */
    @Test
    void testCompareTo() {
        Player otherPlayer = new Player("Bob");
        otherPlayer.stepScore();
        assertTrue(player.compareTo(otherPlayer) < 0, "Alice should have a lower score than Bob.");
        player.stepScore();
        assertEquals(0, player.compareTo(otherPlayer), "Alice and Bob should have the same score.");
        player.stepScore();
        assertTrue(player.compareTo(otherPlayer) > 0, "Alice should have a higher score than Bob.");
    }
}
