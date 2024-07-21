package test.entities;

import main.entities.Player;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static test.TestingHelperFunctions.getPrivateVariableHelper;
import static test.TestingHelperFunctions.setPrivateVariableHelper;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void constructorTest() throws NoSuchFieldException, IllegalAccessException {
        Player player = new Player("A");
        assertEquals("A", getPrivateVariableHelper(player, "name").toString());
        assertEquals(0, (int) getPrivateVariableHelper(player, "score"));
    }

    @org.junit.jupiter.api.Test
    void getNameTestBasic() {
        Player player = new Player("B");
        assertEquals("B", player.getName());
    }

    @org.junit.jupiter.api.Test
    void getNameTestWeirdName() {
        Player player1 = new Player("0010101");
        assertEquals("0010101", player1.getName());
        Player player2 = new Player("%#//\\Æ\n");
        assertEquals("%#//\\Æ\n", player2.getName());
    }

    @org.junit.jupiter.api.Test
    void getAndSetScoreTest() throws NoSuchFieldException, IllegalAccessException {
        Player player = new Player("C");
        assertEquals(0, player.getScore());

        setPrivateVariableHelper(player, "score", 525600);
        assertEquals(525600, player.getScore());
        assertEquals(525600, (int) getPrivateVariableHelper(player, "score"));

        player.setScore(3720);
        assertEquals(3720, player.getScore());
        assertEquals(3720, (int) getPrivateVariableHelper(player, "score"));
    }

    @org.junit.jupiter.api.Test
    void testToStringTest() throws NoSuchFieldException, IllegalAccessException {
        Player player = new Player("D");
        setPrivateVariableHelper(player, "score", 3111994);
        assertEquals("D, 3111994", player.toString());
    }

    @org.junit.jupiter.api.Test
    void compareToTest() throws NoSuchFieldException, IllegalAccessException {
        Player player1 = new Player("E");
        Player player2 = new Player("F");

        // compare different scores
        setPrivateVariableHelper(player1, "score", 7221994);
        setPrivateVariableHelper(player2, "score", 9211995);
        assertEquals(-1, player1.compareTo(player2));
        assertEquals(1, player2.compareTo(player1));

        //compare same scores
        player1.setScore(3263827);
        player2.setScore(3263827);
        assertEquals(0, player1.compareTo(player2));
        assertEquals(0, player2.compareTo(player1));
    }




}