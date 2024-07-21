package test.entities;

import main.entities.Question;
import main.entities.QuestionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static test.TestingHelperFunctions.getPrivateVariableHelper;
import static test.TestingHelperFunctions.setPrivateVariableHelper;

class QuestionListTest {

    @Test
    void constructorTest() throws NoSuchFieldException, IllegalAccessException {
        QuestionList list = new QuestionList();
        assertEquals(10, (int) getPrivateVariableHelper(list, "amount"));
        assertEquals("Any Category", list.getCategory());
        assertEquals("Any Difficulty", list.getDifficulty());
        assertTrue(list.getQuestions().isEmpty());
    }

    @Test
    void constructorTestBasicParameters() throws NoSuchFieldException, IllegalAccessException {
        QuestionList list = new QuestionList(4, "Art", "Hard", "Multiple Choice");
        assertEquals(4, (int) getPrivateVariableHelper(list, "amount"));
        assertEquals("Art", list.getCategory());
        assertEquals("Hard", list.getDifficulty());
        assertTrue(list.getQuestions().isEmpty());
    }

    @Test
    void addQuestionTest() {
        QuestionList list = new QuestionList(1, "Art", "Hard", "Multiple Choice");
        ArrayList<String> incorrectAnswers = new ArrayList<>();
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        Question question = new Question("?", "correct", incorrectAnswers, "Hard", "Art", "Multiple Choice");
        list.addQuestion(question);
        assertEquals(1, list.getQuestions().size());
        assertEquals(question, list.getQuestions().get(0));
    }

    @Test
    void getNextQuestionTest() {
        QuestionList list = new QuestionList(2, "Art", "Hard", "Multiple Choice");

        ArrayList<String> incorrectAnswers = new ArrayList<>();
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        Question question1 = new Question("?", "correct", incorrectAnswers, "Hard", "Art", "Multiple Choice");

        ArrayList<String> incorrectAnswers2 = new ArrayList<>();
        incorrectAnswers2.add("wrong");
        incorrectAnswers2.add("wrong");
        incorrectAnswers2.add("wrong");
        incorrectAnswers2.add("wrong");
        Question question2 = new Question("?", "correct", incorrectAnswers2, "Hard", "Art", "Multiple Choice");

        list.addQuestion(question1);
        list.addQuestion(question2);
        assertEquals(question1, list.getNextQuestion());
        assertEquals(question2, list.getNextQuestion());
        assertNull(list.getNextQuestion());
    }

    @Test
    void hasMoreQuestionsTest() throws NoSuchFieldException, IllegalAccessException {
        QuestionList list = new QuestionList(0, "Art", "Hard", "Multiple Choice");
        assertFalse(list.hasMoreQuestions());
        ArrayList<String> incorrectAnswers = new ArrayList<>();
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        incorrectAnswers.add("wrong");
        Question question = new Question("?", "correct", incorrectAnswers, "Hard", "Art", "Multiple Choice");
        list.addQuestion(question);
        setPrivateVariableHelper(list, "amount", 1);
        assertTrue(list.hasMoreQuestions());
        list.getNextQuestion();
        assertFalse(list.hasMoreQuestions());
    }

    @Test
    void getDifficultyTest() {
        QuestionList defaultList = new QuestionList();
        assertEquals("Any Difficulty", defaultList.getDifficulty());

        QuestionList customList = new QuestionList(4, "Art", "Hard", "Multiple Choice");
        assertEquals("Hard", customList.getDifficulty());
    }
}