package data_access;

import entities.Question;
import entities.QuestionList;
import org.apache.commons.lang3.StringEscapeUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/** Concrete implementation of TriviaDBInterface. */
public class TriviaDB implements TriviaDBInterface {
    /** Client for sending API requests. */
    private final OkHttpClient client;

    /**
     * getQuestions takes in three strings for the category, difficulty and
     * type. converter converts said strings into the parameters used in the
     * API call.
     */
    private final APIParameterConverter converter;

    /**
     * Session token. Using a token prevents the same question from being used
     * multiple times. When the API runs out of questions, the token must be
     * refreshed. The token may be deleted after an extended period of
     * inactivity, at which point a new token must be generated. Instructions
     * for generating and refreshing tokens are documented on opentdb.com.
     */
    private final String token;

    /**
     * Instantiate a DAO by creating a client, APIParameterConverter and
     * retrieving a session token. APIParameterConverter is an internal class
     * which is documented below.
     * */
    public TriviaDB() {
        client = new OkHttpClient();
        converter = new APIParameterConverter();
        token = retrieveToken();
    }

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
    @Override
    public QuestionList getQuestions(int amount, String category, String difficulty, String type) {
        // Generate the url for the API call.
        StringBuilder url = new StringBuilder("https://opentdb.com/api.php?");
        url.append("amount=").append(amount);
        Integer convertedCategory = converter.convertCategory(category);
        String convertedDifficulty = converter.convertDifficulty(difficulty);
        String convertedType = converter.convertType(type);
        if (convertedCategory != null) {
            url.append("&category=").append(convertedCategory);
        }
        if (convertedDifficulty != null) {
            url.append("&difficulty=").append(convertedDifficulty);
        }
        if (convertedType != null) {
            url.append("&type=").append(convertedType);
        }
        url.append("&token=").append(token);
        // Make the API call.
        JSONObject responseBody = this.APICall(url.toString());
        // If there aren't enough questions, refresh the token and try again.
        if (responseBody.getInt("response_code") == 1) {
            this.refreshToken();
            responseBody = this.APICall(url.toString());
        }
        // If anything else unexpected happens, throw a RuntimeException.
        if (responseBody.getInt("response_code") != 0) {
            throw new RuntimeException("Got this from the API call->" + responseBody);
        }
        JSONArray questions = responseBody.getJSONArray("results");
        // Store the questions as a QuestionList.
        QuestionList questionList = new QuestionList(amount, category, difficulty, type);
        for (int i = 0; i < questions.length(); i++) {
            // Make a new Question and add it to questionList.
            JSONObject questionJSON = questions.getJSONObject(i);
            String questionText = StringEscapeUtils.unescapeHtml4(questionJSON.getString("question"));
            String correctAnswer = StringEscapeUtils.unescapeHtml4(questionJSON.getString("correct_answer"));
            JSONArray incorrectAnswers = questionJSON.getJSONArray("incorrect_answers");
            ArrayList<String> incorrectAnswersArray = new ArrayList<>(incorrectAnswers.length());
            for (int j = 0; j < incorrectAnswers.length(); j++) {
                incorrectAnswersArray.add(StringEscapeUtils.unescapeHtml4(incorrectAnswers.getString(j)));
            }
            Question question = new Question(
                    questionText,
                    correctAnswer,
                    incorrectAnswersArray,
                    questionJSON.getString("difficulty"),
                    questionJSON.getString("category"),
                    questionJSON.getString("type"));
            questionList.addQuestion(question);
        }
        return questionList;
    }

    /**
     * Retrieves a new session token.
     *
     * @return the retrieved token.
     */
    private String retrieveToken() {
        JSONObject responseBody = this.APICall("https://opentdb.com/api_token.php?command=request");
        if (responseBody.getInt("response_code") != 0) {
            throw new RuntimeException("Got this from the API call->" + responseBody);
        }
        return responseBody.getString("token");
    }

    /** Refresh the current session token. */
    private void refreshToken() {
        JSONObject responseBody = this.APICall("https://opentdb.com/api_token.php?command=reset&token=" + token);
        if (responseBody.getInt("response_code") != 0) {
            throw new RuntimeException("Got this from the API call->" + responseBody);
        }
    }

    /**
     * Make an API call with the session token and given url.
     *
     * @param url for the API call.
     * @return the response in the form of a JSONObject.
     */
    private JSONObject APICall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.body() == null) {
                throw new RuntimeException("Got this from the API call->" + response);
            }
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts valid strings passed into getQuestions into parameters for the
     * API call. This class is internal as it should only be used in TriviaDB.
     */
    private static class APIParameterConverter {
        /** Map used to convert category to its corresponding parameter. */
        private final HashMap<String, Integer> categoryMap = new HashMap<>(24);

        /** Map used to convert difficulty to its corresponding parameter. */
        private final HashMap<String, String> difficultyMap = new HashMap<>(4);

        /** Map used to convert question type to its corresponding parameter. */
        private final HashMap<String, String> typeMap = new HashMap<>(2);

        /** Instantiates a converter by setting up categoryMap, difficultyMap and typeMap. */
        public APIParameterConverter() {
            makeCategoryMap();
            makeDifficultyMap();
            makeTypeMap();
        }

        /**
         * Converts a valid option for the category parameter of
         * TriviaDB.getQuestions into its corresponding parameter for the API
         * call that retrieves the questions.
         *
         * @param category must be a valid option for the category parameter of
         *                 TriviaDB.getQuestions.
         * @return parameter for the API call.
         */
        public Integer convertCategory(String category) {
            if (categoryMap.containsKey(category)) {
                return categoryMap.get(category);
            } else {
                throw new IllegalArgumentException("Category option \"" + category + "\" not recognized.");
            }
        }

        /**
         * Converts a valid option for the difficulty parameter of
         * TriviaDB.getQuestions into its corresponding parameter for the API
         * call that retrieves the questions.
         *
         * @param difficulty must be a valid option for the difficulty
         *        parameter of TriviaDB.getQuestions.
         * @return parameter for the API call.
         */
        public String convertDifficulty(String difficulty) {
            if (difficultyMap.containsKey(difficulty)) {
                return difficultyMap.get(difficulty);
            } else {
                throw new IllegalArgumentException("Difficulty option \"" + difficulty + "\" not recognized.");
            }
        }

        /**
         * Converts a valid option for the type parameter of
         * TriviaDB.getQuestions into its corresponding parameter for the API
         * call that retrieves the questions.
         *
         * @param type must be a valid option for the type parameter of
         *        TriviaDB.getQuestions.
         * @return parameter for the API call.
         */
        public String convertType(String type) {
            if (typeMap.containsKey(type)) {
                return typeMap.get(type);
            } else {
                throw new IllegalArgumentException("Type option \"" + type + "\" not recognized.");
            }
        }

        /**
         * Put the key value pairs into categoryMap. Each key is a valid option
         * for the category parameter in TriviaDB.getQuestions, while the
         * associated value is the parameter used in the API call that
         * generates the questions.
         */
        private void makeCategoryMap() {
            categoryMap.put("Any Category", null);
            categoryMap.put("General Knowledge", 9);
            categoryMap.put("Entertainment: Books", 10);
            categoryMap.put("Entertainment: Film", 11);
            categoryMap.put("Entertainment: Music", 12);
            categoryMap.put("Entertainment: Musicals & Theatres", 13);
            categoryMap.put("Entertainment: Television", 14);
            categoryMap.put("Entertainment: Video Games", 15);
            categoryMap.put("Entertainment: Board Games", 16);
            categoryMap.put("Science & Nature", 17);
            categoryMap.put("Science: Computers", 18);
            categoryMap.put("Science: Mathematics", 19);
            categoryMap.put("Mythology", 20);
            categoryMap.put("Sports", 21);
            categoryMap.put("Geography", 22);
            categoryMap.put("History", 23);
            categoryMap.put("Politics", 24);
            categoryMap.put("Art", 25);
            categoryMap.put("Celebrities", 26);
            categoryMap.put("Animals", 27);
            categoryMap.put("Vehicles", 28);
            categoryMap.put("Entertainment: Comics", 29);
            categoryMap.put("Science: Gadgets", 30);
            categoryMap.put("Entertainment: Japanese Anime & Manga", 31);
            categoryMap.put("Entertainment: Cartoon & Animations", 32);
        }

        /**
         * Put the key value pairs into difficultyMap. Each key is a valid
         * option for the difficulty parameter in TriviaDB.getQuestions, while
         * the associated value is the parameter used in the API call that
         * generates the questions.
         */
        private void makeDifficultyMap() {
            difficultyMap.put("Any Difficulty", null);
            difficultyMap.put("Easy", "easy");
            difficultyMap.put("Medium", "medium");
            difficultyMap.put("Hard", "hard");
        }

        /**
         * Put the key value pairs into typeMap. Each key is a valid option for
         * the type parameter in TriviaDB.getQuestions, while the associated
         * value is the parameter used in the API call that generates the
         * questions.
         */
        private void makeTypeMap() {
            typeMap.put("Any Type", null);
            typeMap.put("Multiple Choice", "multiple");
            typeMap.put("True / False", "boolean");
        }
    }
}
