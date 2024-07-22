package main.entities;

import java.util.ArrayList;

/** Entity to represent a list of questions. */
public class QuestionList {
    /** Amount of question in the list. */
    private final int amount; //default 10 questions
    /** Category of the questions. */
    private final String category;
    /** Difficulty of the questions. */
    private final String difficulty;
    /** Type of the questions. */
    private final String type;
    /** ArrayList of the questions stored in the list. */
    private final ArrayList<Question> questions;
    /** Index of the next question to be asked. */
    private int currentQuestionIndex;

    /**
     * Initialize a new QuestionList.
     *
     * @param amount of questions in the list.
     * @param category of the questions.
     * @param difficulty of the questions.
     * @param type of the questions.
     */
    public QuestionList(int amount, String category, String difficulty, String type) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.questions = new ArrayList<Question>(amount);
        this.currentQuestionIndex = 0;
    }

    /**
     * Retrieve the ArrayList of questions.
     *
     * @return ArrayList that stores the questions.
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Retrieve the category of the questions stored in the list.
     *
     * @return the category of the questions.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Retrieve the difficulty of the questions stored in the list.
     *
     * @return the difficulty of the questions.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Add the given question to the list.
     *
     * @param question to add to the list.
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    /**
     * Retrieve the next question to ask the players.
     *
     * @return the next question in the list.
     */
    public Question getNextQuestion() {
        if (currentQuestionIndex < this.amount) {
            return questions.get(currentQuestionIndex++);
        }
        return null;
    }

    /**
     * Return whether there are any questions remaining.
     *
     * @return true iff there are questions remaining in the list.
     */
    public boolean hasMoreQuestions() {
        return currentQuestionIndex < this.amount;
    }
}
