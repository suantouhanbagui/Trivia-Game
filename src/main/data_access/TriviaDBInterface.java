package main.data_access;

import main.entities.QuestionList;

import java.io.IOException;

/**
 * Interface for a data access object that interacts with the opentdb.com
 * trivia api to generate trivia questions.
 */
public interface TriviaDBInterface {
    /**
     * Get questions from the API and return them in a QuestionList.
     *
     * @param amount the number of questions to generate. Must be between 1 and
     *        50 inclusive. If the amount exceeds the number of questions that
     *        exist in the database with the given settings, an exception will
     *        be thrown.
     * @param category the category of the generated questions. Must be an item
     *        of QuestionSettings.getCategoryOptions().
     * @param difficulty the difficulty of the generated questions. Must be an
     *        item of QuestionSettings.getDifficultyOptions().
     * @param type the type of the generated questions. Must be an item of
     *        QuestionSettings.getTypeOptions().
     * @return A QuestionList with the given settings.
     * @throws IOException when the database does not have enough questions
     *         with the given settings.
     */
    QuestionList getQuestions(int amount, String category, String difficulty, String type) throws IOException;
}