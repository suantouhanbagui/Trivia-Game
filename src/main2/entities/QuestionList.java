package main2.entities;

import java.util.ArrayList;

/** Entity to represent a list of questions. */
public class QuestionList extends QuestionSettings {
    /** Amount of question in the list. */
    private final int amount; //default 10 questions
    /** ArrayList of the questions stored in the list. */
    private final ArrayList<Question> questions;
    /** Index of the next question to be asked. */
    private int currentQuestionIndex;

    /** Create a QuestionList with default settings. */
    public QuestionList() {
        super(QuestionSettings.getCategoryOptions()[0],
                QuestionSettings.getDifficultyOptions()[0],
                QuestionSettings.getTypeOptions()[0]);
        amount = 10;
        questions = new ArrayList<>(10);
        currentQuestionIndex = 0;
    }

    /**
     * Initialize a new QuestionList.
     *
     * @param amount of questions in the list.
     * @param category of the questions.
     * @param difficulty of the questions.
     * @param type of the questions.
     */
    public QuestionList(int amount, String category, String difficulty, String type) {
        super(category, difficulty, type);
        this.amount = amount;
        this.questions = new ArrayList<>(amount);
        this.currentQuestionIndex = 0;
    }

    /**
     * Retrieve the size of the QuestionList.
     *
     * @return the size of the QuestionList.
     */
    public int size() {
        return amount;
    }

    /**
     * Add the given question to the QuestionList.
     *
     * @param question to add to the QuestionList.
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }


    /**
     * Retrieve the next question to ask the players.
     *
     * @return the next question in the QuestionList.
     */
    public Question next() {
        if (currentQuestionIndex < this.amount) {
            return questions.get(currentQuestionIndex++);
        }
        return null;
    }

    /**
     * Return whether there are any questions remaining.
     *
     * @return true iff there are questions remaining in the QuestionList.
     */
    public boolean hasNext() {
        return currentQuestionIndex < this.amount;
    }

    public int getIndex() {
        return currentQuestionIndex;
    }
}