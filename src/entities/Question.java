package entities;

import java.util.ArrayList;

/** Entity to represent a question. */
public class Question {
    /** Question body text. This is the question the players will be asked. */
    private final String questionText;
    /** Correct answer to the question. */
    private final String correctAnswer;
    /** ArrayList of incorrect answers. */
    private final ArrayList<String> incorrectAnswers;
    /** Difficulty of the question. */
    private final String difficulty;
    /** Category of the question. */
    private final String category;
    /** Type of the question (multiple or boolean). */
    private final String type;

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
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.difficulty = difficulty;
        this.category = category;
        this.type = type;
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

    /**
     * Retrieve the difficulty of the question.
     *
     * @return the difficulty of the question.
     */
    public String getDifficulty(){
        return difficulty;
    }

    /**
     * Retrieve the category of the question.
     *
     * @return the category of the question.
     */
    public String getCategory(){
        return category;
    }

    /**
     * Retrieve the question type.
     *
     * @return the question type.
     */
    public String getType(){
        return type;
    }

}
