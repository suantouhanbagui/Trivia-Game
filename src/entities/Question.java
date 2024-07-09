package entities;

import java.util.ArrayList;

public class Question {
    private final String questionText;
    private final String correctAnswer;
    private final ArrayList<String> incorrectAnswers;
    private final String difficulty;
    private final String category;
    private final String type;

    public Question(String questionText, String correctAnswer,
                    ArrayList<String> incorrectAnswers, String difficulty, String category, String type){
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.difficulty = difficulty;
        this.category = category;
        this.type = type;
    }

    public String getQuestionText(){
        return questionText;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers(){
        return incorrectAnswers;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getCategory(){
        return category;
    }

    public String getType(){
        return type;
    }

}
