package use_case.play;

import main.entities.Player;
import main.entities.Question;
import main.use_case.play.PlayOutputData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int amount = 10;
        int index = 5;
        Question nextQuestion = new Question("Sample Question", "Answer", new ArrayList<>(), "Easy", "General", "Multiple Choice");
        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        Boolean previousCorrect = true;
        String previousAnswer = "Sample Answer";

        // Act
        PlayOutputData playOutputData = new PlayOutputData(amount, index, nextQuestion, players, previousCorrect, previousAnswer);

        // Assert
        assertEquals(amount, playOutputData.getAmount());
        assertEquals(index, playOutputData.getIndex());
        assertEquals(nextQuestion, playOutputData.getNextQuestion());
        assertArrayEquals(players, playOutputData.getPlayers());
        assertEquals(previousCorrect, playOutputData.getPreviousCorrect());
        assertEquals(previousAnswer, playOutputData.getPreviousAnswer());
    }
}
