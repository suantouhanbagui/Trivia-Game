package main.entities;


/**
 * Abstract class for QuestionLike objects (objects that have a category, difficulty, and
 * type).
 */
public abstract class AbstractQuestionLike {

    /** Category of the questions. */
    private final String category;
    /** Difficulty of the questions. */
    private final String difficulty;
    /** Type of the questions. */
    private final String type;

    public AbstractQuestionLike(String category, String difficulty, String type){
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
    }

    /**
     * Retrieve the difficulty associated with the QuestionLike object.
     *
     * @return the difficulty of the QuestionLike object.
     */
    public String getDifficulty(){
        return difficulty;
    }

    /**
     * Retrieve the category associated with the QuestionLike object.
     *
     * @return the category of the QuestionLike object.
     */
    public String getCategory(){
        return category;
    }

    /**
     * Retrieve the question type associated with the QuestionLike object.
     *
     * @return the question type of the QuestionLike object.
     */
    public String getType(){
        return type;
    }

}
