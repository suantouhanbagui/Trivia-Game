package main.entities;


import java.util.ArrayList;

/**
 * Class for objects with question settings (objects that have a category,
 * difficulty, and type).
 */
public class QuestionSettings {

    /** Category of the questions. */
    private final String category;
    /** Difficulty of the questions. */
    private final String difficulty;
    /** Type of the questions. */
    private final String type;
    /** Stores valid categories. */
    private final static ArrayList<String> categoryOptions = new ArrayList<>(24);
    /** Stores valid difficulty options. */
    private final static ArrayList<String> difficultyOptions = new ArrayList<>(4);
    /** Stores valid question types. */
    private final static ArrayList<String> typeOptions = new ArrayList<>(3);

    /** Initialize a QuestionSettings object with default settings. */
    public QuestionSettings() {
        category = getCategoryOptions()[0];
        difficulty = getDifficultyOptions()[0];
        type = getTypeOptions()[0];
    }

    /**
     * Initialize a new QuestionSettings object.
     *
     * @param category the category of the QuestionSettings object.
     * @param difficulty the difficulty of the QuestionSettings object.
     * @param type the question type associated with the QuestionSettings object.
     */
    public QuestionSettings(String category, String difficulty, String type) {
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
    }

    /**
     * Retrieve the difficulty associated with the QuestionSettings object.
     *
     * @return the difficulty of the QuestionSettings object.
     */
    public String getDifficulty(){
        return difficulty;
    }

    /**
     * Retrieve the category associated with the QuestionSettings object.
     *
     * @return the category of the QuestionSettings object.
     */
    public String getCategory(){
        return category;
    }

    /**
     * Retrieve the question type associated with the QuestionSettings object.
     *
     * @return the question type of the QuestionSettings object.
     */
    public String getType(){
        return type;
    }

    /** Retrieve the valid category options.
     *
     * @return a read-only List of the valid options for category.
     */
    public static String[] getCategoryOptions() {
        if (categoryOptions.isEmpty()) {
            categoryOptions.add("Any Category");
            categoryOptions.add("General Knowledge");
            categoryOptions.add("Entertainment: Books");
            categoryOptions.add("Entertainment: Film");
            categoryOptions.add("Entertainment: Music");
            categoryOptions.add("Entertainment: Musicals & Theatres");
            categoryOptions.add("Entertainment: Television");
            categoryOptions.add("Entertainment: Video Games");
            categoryOptions.add("Entertainment: Board Games");
            categoryOptions.add("Science & Nature");
            categoryOptions.add("Science: Computers");
            categoryOptions.add("Science: Mathematics");
            categoryOptions.add("Mythology");
            categoryOptions.add("Sports");
            categoryOptions.add("Geography");
            categoryOptions.add("History");
            categoryOptions.add("Politics");
            categoryOptions.add("Art");
            categoryOptions.add("Celebrities");
            categoryOptions.add("Animals");
            categoryOptions.add("Vehicles");
            categoryOptions.add("Entertainment: Comics");
            categoryOptions.add("Science: Gadgets");
            categoryOptions.add("Entertainment: Japanese Anime & Manga");
            categoryOptions.add("Entertainment: Cartoon & Animations");
        }
        return categoryOptions.toArray(new String[categoryOptions.size()]);
    }

    /** Retrieve the valid difficulty options.
     *
     * @return a read-only List of the valid options for difficulty.
     */
    public static String[] getDifficultyOptions() {
        if (difficultyOptions.isEmpty()) {
            difficultyOptions.add("Any Difficulty");
            difficultyOptions.add("Easy");
            difficultyOptions.add("Medium");
            difficultyOptions.add("Hard");
        }
        return difficultyOptions.toArray(new String[difficultyOptions.size()]);
    }

    /** Retrieve the valid type options.
     *
     * @return a read-only List of the valid options for type.
     */
    public static String[] getTypeOptions() {
        if (typeOptions.isEmpty()) {
            typeOptions.add("Any Type");
            typeOptions.add("Multiple Choice");
            typeOptions.add("True / False");
        }
        return typeOptions.toArray(new String[typeOptions.size()]);
    }
}