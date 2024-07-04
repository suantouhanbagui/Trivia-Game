package entities;

import java.util.ArrayList;

public class QuestionList {
    private ArrayList<Question> questions;
    private int amount = 10; //default 10 questions
    private String category = null;
    private String difficulty = null;
    private String type = null;

    //default settings for a game
    public QuestionList() {
        this.questions = new ArrayList<>(this.amount);
    }
    public QuestionList(int amount, String category, String difficulty, String type) {
        this.amount = amount;
        this.questions = new ArrayList<Question>(amount);
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
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
}
