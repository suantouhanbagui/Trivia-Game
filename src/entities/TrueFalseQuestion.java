package entities;

public class TrueFalseQuestion implements Question {
    private final String questionText;
    private final String correctAnswer; //either True or False
    private final String difficulty;
    private final String category;

    TrueFalseQuestion(String questionText, String correctAnswer,
                      String difficulty, String category) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.category = category;
    }

    public String getQuestionText(){
        return questionText;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getCategory(){
        return category;
    }
}
