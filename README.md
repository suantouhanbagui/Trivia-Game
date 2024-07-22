### How to Run the Program
- To run the game in the console, open the `Main.java` file and click the run button at the top right of the IDE.
  <img src="images/image1.png" alt="Image" width="200"/>
- After running the game, you should see a prompt in the console to start. Instructions to simulate a game between Alice and Bob will follow.
  
  <img src="images/image2.PNG" alt="Image" width="300"/>

### How the Game Works
- This is a two-player trivia game where you select your game settings, and the API will generate random trivia questions. Each player alternates turns answering questions. For each correct answer, a player earns a point. The player with the most points at the end wins the game.

### What Has Been Done
- Currently, some UI components, such as the main menu and quiz gameplay screen, are completed. The game also works in the console. Additionally, we’ve implemented data persistence, saving each game's score to a text file called `result.txt`.
  <img src="images/image3.png" alt="Image" width="400"/>
  <img src="images/image4.png" alt="Image" width="400"/>

### How SOLID Has Been Used
- Single Responsibility Principle: Each of our classes has only one responsibility. For example, instead of having one class that manages both the recording and the retrieval of the results for the game, we have one class that has the responsibility of retrieving the game's results and a different class that has the responsibility of recording the game's results.
- Open/Closed Principle: By using interfaces, we ensured that classes are open for extension, but closed for modification. For example, we created GamePlayFunctionsInterface and had the GamePlayFunctions class implement that interface. By doing this we allowed for possible extension. Other classes can implement the interface or extend the GamePlayFunctions class, such as if we wanted to create a single-player mode or even a ten-player mode. However, we closed the GamePlayFunctions class for modification as it has to implement the functions from the GamePlayFunctionsInterface and we don't need to rewrite the GamePlayFunctions class to implement other game modes into our program.  
- Interface Segregation Principle: Instead of having large general interfaces, we have several smaller specific interfaces such as GamePlayFunctionsInterface and TriviaDBInterface. This ensures that no class will have to implement irrelevant methods of an interface, which is what the Interface Segregation Principle is defined as. 
### What Needs to Be Finished
- Next, we need to integrate our UI with the main game to display questions and capture user input. We also need to implement settings selection in the main menu. Additionally, we may look into creating a single-player mode and potentially adding support for more than two players if time permits.
