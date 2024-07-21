package test.entities;

import main.entities.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    Question question;

    @BeforeEach
    public void setUp() {
        ArrayList<String> incorrectAnswers = new ArrayList<>();
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");

        question = new Question("?", "correct", incorrectAnswers, "Hard", "Art", "Multiple Choice");
    }

    @Test
    void getQuestionTextTest() {
        assertEquals("?", question.getQuestionText());
    }

    @Test
    void getCorrectAnswerTest() {
        assertEquals("correct", question.getCorrectAnswer());
    }

    @Test
    void getIncorrectAnswersTest() {
        assertEquals(4, question.getIncorrectAnswers().size());
        assertEquals("wrong", question.getIncorrectAnswers().get(0));
        assertEquals("wrong", question.getIncorrectAnswers().get(1));
        assertEquals("wrong", question.getIncorrectAnswers().get(2));
    }

    @Test
    void getDifficultyTest() {
        assertEquals("Hard", question.getDifficulty());
    }

    @Test
    void getCategoryTest() {
        assertEquals("Art", question.getCategory());
    }

    @Test
    void getTypeTest() {
        assertEquals("Multiple Choice", question.getType());
    }
}