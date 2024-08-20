package entities;

import main.entities.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the {@link Question} class.
 * These tests ensure that the class is functioning as expected and the core attributes
 * are properly initialized and retrievable.
 */
class QuestionTest {

    private Question question;
    private ArrayList<String> incorrectAnswers;

    /**
     * Set up a Question instance before each test.
     * This method initializes a Question object with sample data that can be reused across different tests.
     */
    @BeforeEach
    void setUp() {
        incorrectAnswers = new ArrayList<>();
        incorrectAnswers.add("Answer 1");
        incorrectAnswers.add("Answer 2");
        incorrectAnswers.add("Answer 3");

        question = new Question("What is the capital of France?", "Paris",
                incorrectAnswers, "easy", "Geography", "multiple-choice");
    }

    /**
     * Test the retrieval of the question body text.
     * This ensures that the question text is correctly stored and retrievable.
     */
    @Test
    void testGetQuestionText() {
        assertEquals("What is the capital of France?", question.getQuestionText(),
                "The question text should be 'What is the capital of France?'.");
    }

    /**
     * Test the retrieval of the correct answer.
     * This ensures that the correct answer is correctly stored and retrievable.
     */
    @Test
    void testGetCorrectAnswer() {
        assertEquals("Paris", question.getCorrectAnswer(),
                "The correct answer should be 'Paris'.");
    }

    /**
     * Test the retrieval of the incorrect answers.
     * This ensures that the list of incorrect answers is correctly stored and retrievable.
     * Also tests that the list contains the expected number of incorrect answers.
     */
    @Test
    void testGetIncorrectAnswers() {
        assertEquals(incorrectAnswers, question.getIncorrectAnswers(),
                "The incorrect answers should match the provided list.");
        assertEquals(3, question.getIncorrectAnswers().size(),
                "The incorrect answers list should contain 3 items.");
    }
}
