package use_case;

import java.util.Scanner;

/**
 * Use case interactor to gather the desired settings for the game.
 */

// some setting combinations can be invalid (ie not enough questions), find a way to handle?

public class EditGameSettings {
    private final int amount;
    private final String category;
    private final String difficulty;
    private final String type;

    /**
     * Assign values to settings for the game.
     * Default settings: amount = 10, category = "General Knowledge", difficulty = "Easy", type = "Multiple Choice".
     * If user wishes to use custom settings, prompts them to specify their own values for each setting.
     */
    public EditGameSettings() {
        System.out.println("Use default settings? Y/N");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            amount = 10;
            category = "General Knowledge";
            difficulty = "Easy";
            type = "Multiple Choice";
        }
        else {
            System.out.println("Please enter question amount (between 1 to 50): ");
            amount = sc.nextInt();
            System.out.println("Question categories available: ");
            System.out.println("General Knowledge, Entertainment: Books,");
            System.out.println("Entertainment: Film, Entertainment: Music,");
            System.out.println("Entertainment: Musicals and Theatres");
            System.out.println("Entertainment: Television, Entertainment: Video Games,");
            System.out.println("Entertainment: Board Games, Science and Nature,");
            System.out.println("Science: Computers, Science: Mathematics, Mythology,");
            System.out.println("Sports, Geography, History, Politics, Art,");
            System.out.println("Celebrities, Animals, Vehicles, Entertainment: Comics,");
            System.out.println("Science: Gadgets, Entertainment: Japanese Anime & Manga,");
            System.out.println("Entertainment: Cartoon & Animations");
            System.out.println("Please enter question category: ");
            sc.nextLine();
            category = sc.nextLine();
            System.out.println("Question difficulties available: Any Difficulty, Easy, Medium, Hard");
            System.out.println("Please enter question difficulty: ");
            difficulty = sc.nextLine();
            System.out.println("Question types available: Any Type, Multiple Choice, True / False");
            System.out.println("Please enter question type: ");
            type = sc.nextLine();
        }
    }
    public int getAmount(){
        return amount;
    }
    public String getCategory(){
        return category;
    }
    public String getDifficulty(){
        return difficulty;
    }
    public String getType(){
        return type;
    }
}
