package entities;

public class Player implements Comparable<Player>{
    private final String name;
    private int score = 0;

    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String toString() {
        return name + ", " + score;
    }

    @Override
    //to compare and see which player has the higher score
    public int compareTo(Player o) {
        Integer a = score;
        Integer b = o.getScore();
        return a.compareTo(b);
    }
}
