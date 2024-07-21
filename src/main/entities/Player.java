package main.entities;

/** Entity to represent a player. */
public class Player implements Comparable<Player>{
    /** Name of the player. */
    private final String name;

    /** Their score. */
    private int score = 0;

    /**
     * Instantiate a new Player with the given name and initial score of 0.
     *
     * @param name of the Player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Retrieve the player's name.
     *
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve the player's current score.
     *
     * @return the player's current score.
     */
    public int getScore(){
        return score;
    }

    /**
     * Set the player's score.
     *
     * @param score Set the player's score to this.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Return a string representation of the player.
     *
     * @return the string representation.
     */
    public String toString() {
        return name + ", " + score;
    }

    /**
     * Compare the two players' scores as Integers.
     *
     * @param other the object to be compared.
     * @return the same value that would be returned by comparing their scores.
     */
    @Override
    //to compare and see which player has the higher score
    public int compareTo(Player other) {
        Integer a = score;
        Integer b = other.getScore();
        return a.compareTo(b);
    }
}
