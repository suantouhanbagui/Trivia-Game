package entities;

public class MCQuestion implements Question{
    private final String questionText;
    private final String correctAnswer;
    private final String[] incorrectAnswers;
    private final String difficulty;
    private final String category;

    public MCQuestion(String questionText, String correctAnswer,
                      String[] incorrectAnswers, String difficulty, String category){
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.difficulty = difficulty;
        this.category = category;
    }

    public String getQuestionText(){
        return questionText;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public String[] getIncorrectAnswers(){
        return incorrectAnswers;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getCategory(){
        return category;
    }
}
