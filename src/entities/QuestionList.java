package entities;

import java.util.ArrayList;

public class QuestionList {
    private final int amount; //default 10 questions
    private final String category;
    private final String difficulty;
    private final String type;
    private final ArrayList<Question> questions;
    private int currentQuestionIndex;

    //default settings for a game
    public QuestionList() {
        amount = 10;
        category = "Any Category";
        difficulty = "Any Difficulty";
        type = "Any Type";
        questions = new ArrayList<>(this.amount);
        currentQuestionIndex = 0;
    }
    public QuestionList(int amount, String category, String difficulty, String type) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.questions = new ArrayList<Question>(amount);
        this.currentQuestionIndex = 0;
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public String getCategory() {
        return category;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public Question getNextQuestion(){
        if (currentQuestionIndex < this.amount){
            return questions.get(currentQuestionIndex++);
        }
        return null;
    }

    public boolean hasMoreQuestions(){
        return currentQuestionIndex < this.amount;
    }
}
