package main.entities;

import java.util.ArrayList;

/** Stores valid question settings. */
public class QuestionSettingOptions {
    /** Stores valid categories. */
    private final static ArrayList<String> category = new ArrayList<>(24);
    /** Stores valid difficulty options. */
    private final static ArrayList<String> difficulty = new ArrayList<>(4);
    /** Stores valid question types. */
    private final static ArrayList<String> type = new ArrayList<>(3);

    /**
     * Access the category options.
     *
     * @return array of String containing the names of the categories.
     */
    public static String[] getCategoryOptions() {
        // Put the options into the categories ArrayList if it hasn't been done already.
        // The implementation of TriviaDB.APIParameterConverter.makeCategoryMap is dependent on the order of the items.
        if (category.isEmpty()) {
            category.add("Any Category");
            category.add("General Knowledge");
            category.add("Entertainment: Books");
            category.add("Entertainment: Film");
            category.add("Entertainment: Music");
            category.add("Entertainment: Musicals & Theatres");
            category.add("Entertainment: Television");
            category.add("Entertainment: Video Games");
            category.add("Entertainment: Board Games");
            category.add("Science & Nature");
            category.add("Science: Computers");
            category.add("Science: Mathematics");
            category.add("Mythology");
            category.add("Sports");
            category.add("Geography");
            category.add("History");
            category.add("Politics");
            category.add("Art");
            category.add("Celebrities");
            category.add("Animals");
            category.add("Vehicles");
            category.add("Entertainment: Comics");
            category.add("Science: Gadgets");
            category.add("Entertainment: Japanese Anime & Manga");
            category.add("Entertainment: Cartoon & Animations");
        }
        return category.toArray(new String[24]);
    }

    public static String[] getDifficultyOptions() {
        if (difficulty.isEmpty()) {
            difficulty.add("Any Difficulty");
            difficulty.add("Easy");
            difficulty.add("Medium");
            difficulty.add("Hard");
        }
        return difficulty.toArray(new String[4]);
    }

    public static String[] getTypeOptions() {
        if (type.isEmpty()) {
            type.add("Any Type");
            type.add("Multiple Choice");
            type.add("True / False");
        }
        return type.toArray(new String[3]);
    }
}
