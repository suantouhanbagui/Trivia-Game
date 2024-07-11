package entities;

import java.util.ArrayList;

public class QuestionList {
    private final int amount; //default 10 questions
    private final String category;
    private final String difficulty;
    private final String type;
    private final ArrayList<Question> questions;

    //default settings for a game
    public QuestionList() {
        amount = 10;
        category = "Any Category";
        difficulty = "Any Difficulty";
        type = "Any Type";
        questions = new ArrayList<>(this.amount);
    }
    public QuestionList(int amount, String category, String difficulty, String type) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.questions = new ArrayList<Question>(amount);
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

    // Method to print questions with answers
    public void printQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            System.out.println("Answer: " + question.getCorrectAnswer());
            System.out.println("Wrong Answer: " + question.getIncorrectAnswers());
            System.out.println(); // Add a blank line for separation
        }
    }
}
