import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private int score;

    protected char sign;

    public Player(String name, char sign) {
        this.name = name;
        this.sign = sign;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public int getMove(ArrayList<Integer> indexOfOpenCells) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int moveIndex = Integer.parseInt(input);
        return moveIndex;
    }

    public String toString() {
        return "Name: " + name + " Score: " + score + " Sign: " + sign;
    }

}
