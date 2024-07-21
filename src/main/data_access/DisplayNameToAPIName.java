package main.data_access;

import java.util.HashMap;

/**
 * Ignore this for now. Will add some proper Javadoc later.
 */
public class DisplayNameToAPIName {
    private final HashMap<String, Integer> categoryMap = new HashMap<>(24);
    private final HashMap<String, String> difficultyMap = new HashMap<>(4);
    private final HashMap<String, String> typeMap = new HashMap<>(2);

    public DisplayNameToAPIName() {
        makeCategoryMap();
        makeDifficultyMap();
        makeTypeMap();
    }

    public Integer convertCategory(String category) {
        if (categoryMap.containsKey(category)) {
            return categoryMap.get(category);
        } else {
            throw new RuntimeException("Category option \"" + category + "\" not recognized.");
        }
    }

    public String convertDifficulty(String difficulty) {
        if (difficultyMap.containsKey(difficulty)) {
            return difficultyMap.get(difficulty);
        } else {
            throw new RuntimeException("Difficulty option \"" + difficulty + "\" not recognized.");
        }
    }

    public String convertType(String type) {
        if (typeMap.containsKey(type)) {
            return typeMap.get(type);
        } else {
            throw new RuntimeException("Type option \"" + type + "\" not recognized.");
        }
    }

    private void makeCategoryMap() {
        categoryMap.put("Any Category", null);
        categoryMap.put("General Knowledge", 9);
        categoryMap.put("Entertainment: Books", 10);
        categoryMap.put("Entertainment: Film", 11);
        categoryMap.put("Entertainment: Music", 12);
        categoryMap.put("Entertainment: Musicals & Theatres", 13);
        categoryMap.put("Entertainment: Television", 14);
        categoryMap.put("Entertainment: Video Games", 15);
        categoryMap.put("Entertainment: Board Games", 16);
        categoryMap.put("Science & Nature", 17);
        categoryMap.put("Science: Computers", 18);
        categoryMap.put("Science: Mathematics", 19);
        categoryMap.put("Mythology", 20);
        categoryMap.put("Sports", 21);
        categoryMap.put("Geography", 22);
        categoryMap.put("History", 23);
        categoryMap.put("Politics", 24);
        categoryMap.put("Art", 25);
        categoryMap.put("Celebrities", 26);
        categoryMap.put("Animals", 27);
        categoryMap.put("Vehicles", 28);
        categoryMap.put("Entertainment: Comics", 29);
        categoryMap.put("Science: Gadgets", 30);
        categoryMap.put("Entertainment: Japanese Anime & Manga", 31);
        categoryMap.put("Entertainment: Cartoon & Animations", 32);
    }

    private void makeDifficultyMap() {
        difficultyMap.put("Any Difficulty", null);
        difficultyMap.put("Easy", "easy");
        difficultyMap.put("Medium", "medium");
        difficultyMap.put("Hard", "hard");
    }

    private void makeTypeMap() {
        typeMap.put("Any Type", null);
        typeMap.put("Multiple Choice", "multiple");
        typeMap.put("True / False", "boolean");
    }
}
