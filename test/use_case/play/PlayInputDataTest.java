package use_case.play;

import static org.junit.jupiter.api.Assertions.*;

import main.use_case.play.PlayInputData;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link PlayInputData} class.
 */
public class PlayInputDataTest {

    /**
     * Test constructor and getter method.
     * Verifies that the {@link PlayInputData} object is correctly initialized
     * and the getter method returns the expected answer.
     */
    @Test
    public void testConstructorAndGetter() {
        // Arrange
        String expectedAnswer = "42";
        PlayInputData playInputData = new PlayInputData(expectedAnswer);

        // Act
        String actualAnswer = playInputData.getAnswer();

        // Assert
        assertEquals(expectedAnswer, actualAnswer, "The answer returned by getAnswer() should match the answer provided in the constructor.");
    }

    /**
     * Test handling of null values.
     * Verifies that the constructor and getter can handle a null value for the answer.
     */
    @Test
    public void testConstructorAndGetterWithNull() {
        // Arrange
        PlayInputData playInputData = new PlayInputData(null);

        // Act
        String actualAnswer = playInputData.getAnswer();

        // Assert
        assertNull(actualAnswer, "The answer should be null when a null value is provided in the constructor.");
    }
}
