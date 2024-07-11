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


/**
 * Concrete implementation of DBInterface.
 */
public class TriviaDB implements TriviaDBInterface {
    private final OkHttpClient client;
    private final DisplayNameToAPIName converter;
    private final String token;

    public TriviaDB() {
        client = new OkHttpClient();
        converter = new DisplayNameToAPIName();
        token = retrieveToken();
    }

    @Override
    public QuestionList getQuestions(int amount, String category, String difficulty, String type) {
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
        JSONObject responseBody = this.APICall(url.toString());
        if (responseBody.getInt("response_code") == 1) {
            this.refreshToken();
            responseBody = this.APICall(url.toString());
        }
        if (responseBody.getInt("response_code") != 0) {
            throw new RuntimeException("Got this from the API call->" + responseBody);
        }
        JSONArray questions = responseBody.getJSONArray("results");
        QuestionList questionList = new QuestionList(amount, category, difficulty, type);
        for (int i = 0; i < questions.length(); i++) {
            JSONObject questionJSON = questions.getJSONObject(i);
            JSONArray incorrectAnswers = questionJSON.getJSONArray("incorrect_answers");
            ArrayList<String> incorrectAnswersArray = new ArrayList<>(incorrectAnswers.length());
            for (int j = 0; j < incorrectAnswers.length(); j++) {
                incorrectAnswersArray.add(StringEscapeUtils.unescapeHtml4(incorrectAnswers.getString(j)));
            }
            String questionText = StringEscapeUtils.unescapeHtml4(questionJSON.getString("question"));
            String correctAnswer = StringEscapeUtils.unescapeHtml4(questionJSON.getString("correct_answer"));

            Question question = new Question(questionText,
                    correctAnswer,
                    incorrectAnswersArray,
                    questionJSON.getString("difficulty"),
                    questionJSON.getString("category"),
                    questionJSON.getString("type"));
            questionList.addQuestion(question);
        }
        return questionList;
    }

    private String retrieveToken() {
        JSONObject responseBody = this.APICall("https://opentdb.com/api_token.php?command=request");
        if (responseBody.getInt("response_code") != 0) {
            throw new RuntimeException("Got this from the API call->" + responseBody);
        }
        return responseBody.getString("token");
    }

    private void refreshToken() {
        JSONObject responseBody = this.APICall("https://opentdb.com/api_token.php?command=reset&token=" + token);
        if (responseBody.getInt("response_code") != 0) {
            throw new RuntimeException("Got this from the API call->" + responseBody);
        }
    }

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
}
