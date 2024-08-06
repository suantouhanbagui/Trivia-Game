package main2.data_access;

import main2.entities.QuestionSettings;
import main2.entities.Question;
import main2.entities.QuestionList;
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
    @Override
    public QuestionList getQuestions(int amount, String category, String difficulty, String type) throws IOException {
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
        if (responseBody.getInt("response_code") == 4) {
            throw new IOException("There are not enough questions with the selected settings.");
        } else if (responseBody.getInt("response_code") != 0) {
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
        private APIParameterConverter() {
            // Put key-value pairs into categoryMap.
            String[] keys = QuestionSettings.getCategoryOptions();
            categoryMap.put("Any Category", null);
            for (int i = 1; i < keys.length; i++) {
                categoryMap.put(keys[i], i + 8);
            }
            // Put key-value pairs into difficultyMap.
            difficultyMap.put("Any Difficulty", null);
            difficultyMap.put("Easy", "easy");
            difficultyMap.put("Medium", "medium");
            difficultyMap.put("Hard", "hard");
            // Put key-value pairs into typeMap.
            typeMap.put("Any Type", null);
            typeMap.put("Multiple Choice", "multiple");
            typeMap.put("True / False", "boolean");
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
    }
}