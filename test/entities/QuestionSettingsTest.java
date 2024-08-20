package entities;

import static org.junit.jupiter.api.Assertions.*;

import main.entities.QuestionSettings;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Unit tests for the {@link QuestionSettings} class.
 */
public class QuestionSettingsTest {

    /**
     * Test default constructor initialization.
     * Verifies that the {@link QuestionSettings} object is initialized with default values.
     */
    @Test
    public void testDefaultConstructor() {
        QuestionSettings settings = new QuestionSettings();
        assertEquals("Any Category", settings.getCategory(), "Default category should be 'Any Category'.");
        assertEquals("Any Difficulty", settings.getDifficulty(), "Default difficulty should be 'Any Difficulty'.");
        assertEquals("Any Type", settings.getType(), "Default type should be 'Any Type'.");
    }

    /**
     * Test parameterized constructor initialization.
     * Verifies that the {@link QuestionSettings} object is initialized with provided values.
     */
    @Test
    public void testParameterizedConstructor() {
        QuestionSettings settings = new QuestionSettings("Science", "Medium", "Multiple Choice");
        assertEquals("Science", settings.getCategory(), "Category should be 'Science'.");
        assertEquals("Medium", settings.getDifficulty(), "Difficulty should be 'Medium'.");
        assertEquals("Multiple Choice", settings.getType(), "Type should be 'Multiple Choice'.");
    }

    /**
     * Test retrieval of category options.
     * Verifies that the {@link QuestionSettings#getCategoryOptions()} method returns the correct category options.
     */
    @Test
    public void testGetCategoryOptions() {
        String[] categories = QuestionSettings.getCategoryOptions();
        assertTrue(categories.length > 0, "There should be at least one category option.");
        assertTrue(Arrays.asList(categories).contains("Science & Nature"), "Category options should include 'Science & Nature'.");
    }

    /**
     * Test retrieval of difficulty options.
     * Verifies that the {@link QuestionSettings#getDifficultyOptions()} method returns the correct difficulty options.
     */
    @Test
    public void testGetDifficultyOptions() {
        String[] difficulties = QuestionSettings.getDifficultyOptions();
        assertTrue(difficulties.length > 0, "There should be at least one difficulty option.");
        assertTrue(Arrays.asList(difficulties).contains("Hard"), "Difficulty options should include 'Hard'.");
    }

    /**
     * Test retrieval of type options.
     * Verifies that the {@link QuestionSettings#getTypeOptions()} method returns the correct type options.
     */
    @Test
    public void testGetTypeOptions() {
        String[] types = QuestionSettings.getTypeOptions();
        assertTrue(types.length > 0, "There should be at least one type option.");
        assertTrue(Arrays.asList(types).contains("True / False"), "Type options should include 'True / False'.");
    }

    /**
     * Test static list initialization.
     * Verifies that the static lists are correctly initialized when the retrieval methods are called.
     */
    @Test
    public void testStaticListInitialization() {
        // Trigger static list initialization
        String[] categories = QuestionSettings.getCategoryOptions();
        String[] difficulties = QuestionSettings.getDifficultyOptions();
        String[] types = QuestionSettings.getTypeOptions();

        // Check if lists have been initialized correctly
        assertFalse(categories.length == 0, "Category options should be initialized.");
        assertFalse(difficulties.length == 0, "Difficulty options should be initialized.");
        assertFalse(types.length == 0, "Type options should be initialized.");
    }
}
