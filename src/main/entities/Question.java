package main.entities;

import java.util.ArrayList;

/** Entity to represent a question. */
public class Question extends AbstractQuestionLike{
    /** Question body text. This is the question the players will be asked. */
    private final String questionText;
    /** Correct answer to the question. */
    private final String correctAnswer;
    /** ArrayList of incorrect answers. */
    private final ArrayList<String> incorrectAnswers;

    /**
     * Create a mew question with the given parameters.
     *
     * @param questionText Question body.
     * @param correctAnswer to the question.
     * @param incorrectAnswers ArrayList of incorrect answers.
     * @param difficulty of the question.
     * @param category of the question.
     * @param type of the question.
     */
    public Question(String questionText, String correctAnswer,
                    ArrayList<String> incorrectAnswers, String difficulty, String category, String type){
        super(category, difficulty, type);
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    /**
     * Retrieve the question body text.
     *
     * @return the question body text.
     */
    public String getQuestionText(){
        return questionText;
    }

    /**
     * Retrieve the correct answer for the question.
     *
     * @return the correct answer for the question.
     */
    public String getCorrectAnswer(){
        return correctAnswer;
    }

    /**
     * Retrieve the incorrect answers for the question.
     *
     * @return an ArrayList of the incorrect answers.
     */
    public ArrayList<String> getIncorrectAnswers(){
        return incorrectAnswers;
    }

}
