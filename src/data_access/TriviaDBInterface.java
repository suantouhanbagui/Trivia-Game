package data_access;

import entities.QuestionList;

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
     * @param category the category of the generated questions. Must be one of
     *        the following: "General Knowledge", "Entertainment: Books",
     *        "Entertainment: "Film", "Entertainment: Music",
     *        "Entertainment: Musicals & Theatres",
     *        "Entertainment: Television", "Entertainment: Video Games",
     *        "Entertainment: Board Games", "Science & Nature",
     *        "Science: Computers", "Science: Mathematics", "Mythology",
     *        "Sports", "Geography", "History", "Politics", "Art",
     *        "Celebrities", "Animals", "Vehicles", "Entertainment: Comics",
     *        "Science: Gadgets", "Entertainment: Japanese Anime & Manga",
     *        "Entertainment: Cartoon & Animations".
     * @param difficulty the difficulty of the generated questions. Must be one
     *        of the following: "Any Difficulty", "Easy", "Medium", "Hard".
     * @param type the type of the generated questions. Must be one of the
     *        following: "Any Type", "Multiple Choice", "True / False".
     * @return A QuestionList with the given settings.
     */
    QuestionList getQuestions(int amount, String category, String difficulty, String type);
}
