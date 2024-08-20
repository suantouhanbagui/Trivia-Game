package main.data_access;

import main.entities.Question;
import main.entities.QuestionList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TriviaDBTest {

    private TriviaDB triviaDB;

    @BeforeEach
    public void setUp() {
        triviaDB = new TriviaDB() {
            // Mock API call with different question each time

            protected JSONObject APICall(String url) {
                // Provide a fixed mock response for consistency in tests
                String jsonResponse = "{\n" +
                        "    \"response_code\": 0,\n" +
                        "    \"results\": [\n" +
                        "        {\n" +
                        "            \"question\": \"What is the highest mountain in the world?\",\n" +
                        "            \"correct_answer\": \"Mount Everest\",\n" +
                        "            \"incorrect_answers\": [\"K2\", \"Kangchenjunga\", \"Lhotse\"],\n" +
                        "            \"difficulty\": \"Easy\",\n" +
                        "            \"category\": \"Sports\",\n" +
                        "            \"type\": \"Multiple Choice\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                return new JSONObject(jsonResponse);
            }
        };
    }

    @Test
    public void testGetQuestions() throws IOException {
        // Execute method
        QuestionList questionList = triviaDB.getQuestions(1, "Sports", "Easy", "Multiple Choice");

        // Verify results
        assertEquals(1, questionList.size(), "The QuestionList should contain 1 question.");

        Question question = questionList.next(); // Use next() to get the question
        assertNotNull(question, "The question should not be null.");

        // Validate question details
        assertNotNull(question.getQuestionText(), "The question text should not be null.");
        assertNotNull(question.getCorrectAnswer(), "The correct answer should not be null.");
        ArrayList<String> incorrectAnswers = question.getIncorrectAnswers();
        assertFalse(incorrectAnswers.isEmpty(), "The incorrect answers should not be empty.");
        assertEquals(3, incorrectAnswers.size(), "There should be 3 incorrect answers.");
    }
}
